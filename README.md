### Macao Server UI SDK
Macao is a **server-driven-ui** SDK, it allows you to change the look and feeling, and the behavior of an Application from a remote json configuration file. Bypassing the Google and Apple stores offering a seamless update experince to the users.
<BR/>
One of the goals of the Macao SDK is **easy of extendibility**, its architecture allows collaborators to easily incorporate new features into the SDK, as plug-ins that get injected in the SDK using koin modules.

### Tech Used
- <ins>Koin</ins>
- <ins>Compose Navigation</ins>
- <ins>Compose Lifecycle-ViewModel</ins>
- <ins>Ktor</ins>(coming soon)
- <ins>Room</ins>(coming soon)
- <ins>Coil</ins>(coming soon)

### Supported Targets
Android, iOS, JVM and JS, **Wasm not supported yet**

---
### Core concepts
- #### DestinationInfo
  A data class that defines a destination in our domain. It contains the metadata associated to a compose destination. The 
  DestinationInfo model is usually loaded remotely as users navigate the nav graph. This metadata can change from time to time so 
  it is important to set a considerable cache time depending how often you plan to update your App.
  
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

      // To pass input params from parents down to children.
      // Basically is the "Props" pattern taken from ReactJS.
      var props: Bundle? = null
    )

  ```

- #### DestinationRender
  A `DestinationRender` is intended to be a function that knows how to render a specific destination type. We decided to use an 
  interface for easy binding with `koin` but implementaions of this interface must not contain state at all.
  
  ```kotlin
  
    interface DestinationRender {

      fun getRenderType(): String

      @Composable
      fun Content(
        destinationInfo: DestinationInfo,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        resultHandler: ResultHandler<DestinationResult<*>>
      )
    }
  
  ```
  Examples implementations are `DrawerDestinationRender` or `BottomNavigationRender` or `MyCustomScreenDestinationRender`.

- #### RootDestinationRender
  Similar to a `DestinationRender` but its composable render function does not receive neither a navController nor a 
  navBackStackEntry because it is a root Composable and it doesn't need those.
  ```kotlin
  
    interface RootDestinationRender {

      fun getRenderType(): String

      @Composable
      fun Content(
        destinationInfo: DestinationInfo,
        viewModelStoreOwner: ViewModelStoreOwner
      )
    }
  
  ```

- #### DestinationRendersRegistry
  `DestinationRendersRegistry` is basically a mapper of `DestinationInfo` and its `DestinationRender` types. It would resolve the 
  right `DestinationRender` or `RootDestinationRender` implementation for a given DestinationInfo `renderType: String`.
  Each `DestinationInfo` must define what type of renderType will handle its rendering. This type has to match at least one 
  `DestinationRender` registered in the registry. The DestinationRender implementations have to be defined at build time and 
  registered in the **DestinationRendersRegistry** before a DestinationRender asks for it.
  **Reflection in Kotlin Native doesn't allow instantiating a class given its String name, so we have to register each 
  DestinationRender at build time. Koin certainly helps to achieve this using the module scanning feature**

- #### RootGraphInitializer
  As its name indicates the purpose of classes implementing this interface, is basically remotely load the intial navigation 
  graph destination metadata.
  ```kotlin
  
    interface RootGraphInitializer {
      
      fun shouldShowLoader(): Boolean
      
      suspend fun initialize(
        koinComponent: KoinComponent
      ): MacaoResult<DestinationInfo, RootGraphInitializerError>
    }
  
   ```
  The `RootGraphInitializer` will remotely load the necessary metadata to determine who will be the 
  `RootDestinationRender` of the App. In the `initialize(...)` function it gets provided with the **root** `koinComponent: 
  KoinComponent`. So it can get whatever dependency it needs from it. Eg: HttpService, Repository to fetch the initial 
  Destination metadata.

- #### RootKoinModuleInitializer
  This interface is defined as:
   ```kotlin
   interface RootKoinModuleInitializer {
     suspend fun initialize() : List<Module>
   }
   ```
  The `RootKoinModuleInitializer` implementation will be in charge of setting up the minimum necessary Koin dependencies for the 
  App to complete the Startup process. These dependencies usually include a network library to access the `app-config.json` file 
  remotely defined, and a local database to save and cache the Destinations metadata retrieved remotely.
  See `CommonKoinModulesInitializer` class and the specific platform subclasses like `AndroidKoinModulesInitializer` to see how 
  to add dependencies that belong to **commonMain** or to each specific platform.

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
    suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Unit, StartupTaskError>
}
```
The task execution doesn't necessarely has to do heavy work all the time. Some times the task knows it has some data cached and the next executione will take a few milliseconds. In this case, the StartupTask can return `false` in the function `fun shouldShowLoader(): Boolean` and the loader in the `SplashScreen` wont be shown. 
<BR/>
**If no task do heavy work(all them return false), then we omit the SplashScreen animation**


### Expanding the Macao Marketplace
Adding a new screen(destination) to the library is a matter of completing a few steps. Lets do a **Drawer** as an example.

#### Drawer
**1-** The first thing is create our custom `DemoDrawerViewModel` class which extends from `DrawerViewModel` which extends from `DestinationViewModel` which extends from our beloved Jetpack `ViewModel`.

Your ViewModel will look like this:
```kotlin

    class DemoDrawerViewModel(
      private val drawerDataSource: DemoDrawerDataSource,
      override val drawerStatePresenter: DrawerStatePresenterDefault,
      override val destinationRendersRegistry: DestinationRendersRegistry
    ) : DrawerViewModel() {

      private val coroutineScope = CoroutineScope(Dispatchers.Main)
  
      override fun onStart(destinationInfo: DestinationInfo) {
        println("DemoDrawerViewModel[${instanceId()}]::onStart()")

        coroutineScope.launch {
            val childDestinations = stateLoaderUseCase
                .loadChildrenDestinations(destinationInfo.dataSource)
            val navItemDecoNewList = childDestinations.map {
                it.toDrawerNavItem()
            }
            drawerStatePresenter.setNavItemsDeco(navItemDecoNewList)
        }
      }

      override fun onStop() {
        println("DemoDrawerViewModel[${instanceId()}]::onStop()")
      }

      override fun handleBackPressed() {
        println("DemoDrawerViewModel[${instanceId()}]::handleBackPressed()")
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

**3-** The third step is providing a Koin module that creates the instances of your classes
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

**4-** And finally we need to add the newly created koin module in the `CommonKoinModulesInitializer`

```kotlin

  abstract class CommonKoinModulesInitializer(
    private val ioDispatcher: CoroutineDispatcher = getCoroutineDispatchers().io
  ) : KoinModulesInitializer {

    override suspend fun initialize(): List<Module> = withContext(ioDispatcher) {
        commonKoinModules() + platformKoinModules()
    }

    private suspend fun commonKoinModules(): List<Module> = mutableListOf<Module>().apply {

      ...

      // Drawer module
      add(drawerModule)

      ...
    }
}

```

And that was it. Above steps will give you something like shown in the gif image bellow:

<img width="500" src="https://github.com/pablichjenkov/macao-sdk-navigation-compose/assets/5303301/f11e8057-d995-460f-94e1-2b535afaba99">

### BottomNavigation
In Progress ...
