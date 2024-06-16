### Macao Server UI SDK
Macao SDK allows you to create and extend an Application from a remote json file. It offers ways to change the lookand feeling of such an App without the need to go through the Google and Apple stores.
<BR/>
The Macao SDK already exist but is implemented with a custom architecture, navigation and components library. However, the implementation in this project, is purely fabricated on top of the **multiplatform** version of **Android Architecture Components**.

### Tech Used
- <ins>Compose Navigation</ins>
- <ins>Compose ViewModel</ins>
- <ins>Koin</ins>
- <ins>Ktor</ins>(coming soon)
- <ins>Room</ins>(coming soon)
- <ins>Coil</ins>(coming soon)

### Supported Targets
Android, iOS, JVM and JS, **Wasm not supported**

---
### Core concepts
- #### DestinationInfo
  Model that defines the destination in our domain. These destinations are usually setup in the server remotely and their data may vary per App launch. Currently the schema looks like bellow but it will keep adding more fields as it needs them.
  ```kotlin
    data class DestinationInfo(
    // Remote Data
    val route: String,
    val renderType: String,
    val dataSource: String,

    // Presentation
    val label: String,
    val icon: ImageVector,
    val badgeText: String? = null,

    // Local Parameters, copying from ReactJS, used to pass properties from parent
    // to child Composable function.
    var props: Bundle? = null
)

  ```

- #### DestinationRender
  This is an interface with an abstract function:
  ```kotlin
    interface DestinationRender {

      fun getRenderType(): String

      @Composable
      fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
      )
    }
  ```
  Implementations could be for instance `DrawerDestinationRender` or `BottomNavigationRender` or `MyCustomScreenDestinationRender`.

- #### RootDestinationRender
  Similar to the `DestinationRender` but its composable Render function does not receive neither a navController nor a navBackStackEntry because it is a root and it doesn't need it.
  ```kotlin
    interface RootDestinationRender {

      fun getRenderType(): String

      @Composable
      fun Content(destinationInfo: DestinationInfo)
    }
  ```

- #### DestinationRendersRegistry
  Implementation of this interface should resolve the right `DestinationRender` or `RootDestinationRender` given a destination `renderType: String`.
  Each `DestinationInfo` must define what type of renderType will handle its rendering. This type has to match at least one registered `DestinationRender` or `RootDestinationRender`. The destination renderes implementations have to be defined at build time and registered explicitly by code in the registry.
  **Reflection in Kotlin Native doesn't allow instantiating a class given its String name, so we have to register each DestinationRender manually.**

- #### RootGraphInitializer
  This interface is defined as:
  ```kotlin
    interface RootGraphInitializer {
      
      fun shouldShowLoader(): Boolean
      
      suspend fun initialize(
        koinComponent: KoinComponent
      ): MacaoResult<DestinationInfo>
    }
   ```
  The `RootGraphInitializer` implementation will remotely load the necessary metadata to determine who will be the `RootDestinationRender` of the App. In the `initialize(...)` function it gets provided with the **root** `koinComponent: KoinComponent`. So it can get whatever dependency it needs from it. Eg: HttpService, Repository to fetch the initial Destination metadata.

- #### RootKoinModuleInitializer
  This interface is defined as:
   ```kotlin
   interface RootKoinModuleInitializer {
     suspend fun initialize() : List<Module>
   }
   ```
  The `RootKoinModuleInitializer` implementation will be in charge of setting up the minimum necessary Koin dependencies for the App to complete the Startup process. These dependencies usually include a network library to access the `app-config.json` file remotely defined, and a local database to save and cache the Destinations metadata retrieved remotely.

- #### StartupTask
  A StartupTask is a task that runs anytime the Application is launched, either tapping on the App icon or tapping on a notification.
  <BR/>
  These tasks perform the fundamental operations for the App to properly function. Example of this task are:
  1. Database migration
  2. Third party SDKs initialization
  3. Feature flags toggles, for example LaunchDarkly
  4. Other App configurations in your server
   
```kotlin
interface StartupTask {

    fun name(): String

    /**
     * This function dictates whether the initialization will actually take place
     * or it will rely on cached computations from previous launches.
     * */
    fun shouldShowLoader(): Boolean

    /**
     * This function should be executed in io/default dispatcher.
     * Things like Database Migration and LaunchDarkly initialization
     * are examples of StartupTasks.
     * */
    suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Unit>
}
```
The task execution doesn't necessarely has to do heavy work all the time. Some times the task knows it has some data cached and the next executione will take a few milliseconds. In this case, the StartupTask can return `false` in the function `fun shouldShowLoader(): Boolean` and the loader in the `SplashScreen` wont be shown. 
<BR/>
**If no task do heavy work(all them return false), then we omit the SplashScreen animation**


### Expanding the Macao Marketplace
Basically adding a new screen(destination) to the library is a matter of completing a few steps. Lets do a **Drawer** as an example.

#### Drawer
**1-** The first thing is create our custom ViewModel class which extends from `DestinationViewModel` which extends from `Jetpack ViewModel`.

Your ViewModel will look like this:
```kotlin
class DemoDrawerViewModel(
    private val drawerDataSource: DemoDrawerDataSource,
    override val drawerStatePresenter: DrawerStatePresenterDefault,
    override val destinationRendersRegistry: DestinationRendersRegistry
) : DrawerViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
  
    override fun onAttach(destinationInfo: DestinationInfo) {
        println("DemoDrawerViewModel[${instanceId()}]::onAttach()")
        coroutineScope.launch {
            val childDestinations = drawerDataSource.loadDestinations()
            val navItemDecoNewList = childDestinations.map {
                it.toDrawerNavItem()
            }
            drawerStatePresenter.setNavItemsDeco(navItemDecoNewList)
        }
    }

    override fun onStart() {
        println("DemoDrawerViewModel[${instanceId()}]::onStart()")
    }

    override fun onStop() {
        println("DemoDrawerViewModel[${instanceId()}]::onStop()")
    }

    override fun onDetach() {
        println("DemoDrawerViewModel[${instanceId()}]::onDetach()")
    }

    override fun handleBackPressed() {
    }

}
```

**2-** Once your custom `DrawerViewModel` is defined, then we are going to do the custom `DestinationRender`, in this case lets do a `RootDestinationRender` for simplicity.
Notice bellow how the integration with `Koin` allows us to inject the ViewModel instance and scope it to the nearest NavBackStackEntry.

```Kotlin
class DemoDrawerRootDestinationRender : RootDestinationRender {

    override fun getRenderType(): String = ServerUiConstants.ComponentType.Drawer

    @Composable
    override fun Content(rootDestinationInfo: DestinationInfo) {

        val viewModel = koinViewModel<DemoDrawerViewModel>()
        DrawerView(viewModel)
    }
}

```

**3-** The last step is providing a Koin module that creates the instances of your classes
```kotlin
internal val drawerRootDestinationModule = module {

  factoryOf(::DemoDrawerDataSource)
  factory<DrawerStatePresenterDefault> {
    DrawerComponentDefaults.createDrawerStatePresenter()
  }

  // DrawerViewModel
  viewModelOf(::DemoDrawerViewModel)

  factory { DemoDrawerRootDestinationRender() } bind (RootDestinationRender::class)
}
```
The `bind (RootDestinationRender::class)` is a must for Koin to include it in a list of all
DestinationRender implementations. We then get that list and register all the renders in the
`DestinationRendersRegistry`.

**4-** And that was it. Above steps will give you something like shown in the gif image bellow:

<img width="500" src="https://github.com/pablichjenkov/macao-sdk-navigation-compose/assets/5303301/f11e8057-d995-460f-94e1-2b535afaba99">

### BottomNavigation
In Progress ...
