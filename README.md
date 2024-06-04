### Macao Jetpack Navigation
Refactor the whole Macao SDK architecture and components using jetpack navigation-compose.

#### New architecture, new concepts:
- ##### DestinationInfo
  Model that defines the destination in our domain. These destinations are usually setup in the server remotely and their data may vary per App launch. Currently the schema looks like bellow but it will keep adding more fields as it needs them.
  ```kotlin
    data class DestinationInfo(
      val route: String,
      val renderType: String,
      val dataSource: String,
      val label: String,
      val icon: ImageVector,
      val badgeText: String? = null
    )
  ```

- ##### DestinationRender
  This is an interface with an abstract function:
  ```kotlin
    @Composable
    fun Content(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )
  ```
  Implementations could be for instance `DrawerDestinationRender` or `BottomNavigationRender` or `MyCustomScreenDestinationRender`.

- ##### RootDestinationRender
  Similar to the `DestinationRender` but its composable Render function does not receive neither a navController nor a navBackStackEntry because it is a root and it doesn't need it.
  ```kotlin
    @Composable
    fun Content() 
  ```

- ##### DestinationRendersRegistry
  Implementation of this interface should resolve the right `DestinationRender` or `RootDestinationRender` given a destination `renderType: String`.
  Each `DestinationInfo` must define what type of renderType will handle its rendering. This type has to match at least one registered `DestinationRender` or `RootDestinationRender`. The destination renderes implementations have to be defined at build time and registered explicitly by code in the registry.
  **Reflection in Kotlin Native doesn't allow instantiating a class given its String name, so we have to register each DestinationRender manually.**

- ##### RootGraphInitializer
  This interface is defined as:
  ```kotlin
    suspend fun initialize(
        koinComponent: KoinComponent
    ): MacaoResult<RootDestinationRender>
   ```
  The `RootGraphInitializer` implementation will remotely load the necessary metadata to determine who will be the `RootDestinationRender` of the App. In the `initialize(...)` function it gets provided with the **root** `koinComponent: KoinComponent`. So it can get whatever dependency it needs from it. Eg: HttpService, Repository to fetch the initial Destination metadata.

- ##### RootKoinModuleInitializer
  This interface is defined as:
   ```kotlin
   interface RootKoinModuleInitializer {
     suspend fun initialize() : List<Module>
   }
   ```
  The `RootKoinModuleInitializer` implementation will be in charge of setting up the minimum necessary Koin dependencies for the App to complete the Startup process. These dependencies usually include a network library to access the `app-config.json` file remotely defined, and a local database to save and cache the Destinations metadata retrieved remotely.

#### Coming from Macao SDK
If familiar with the **plain** compose **Macao SDK**, this mapping bellow can help to grasp the **macao-jetpack-navigation** concepts.

| Raw Compose         | Jetpack Navigation    |
| ------------------- | --------------------- |
| RootComponent       | RootDestinationRender |
| Component           | DestinationRender     |
| NavigationComponent | DestinationRender     |

#### How it works
Basically adding a new screen(destination) to the library is a matter of completing a few steps. Lets do a **Drawer** as an example.
### Drawer
**1-** The first thing is create our custom ViewModel class which extends from `ComponentViewModel` which extends from `Jetpack ViewModel`.
**ComponentViewModel will potentially change its name to DestinationViewModel**
Your ViewModel will look like this:
```kotlin
class DemoDrawerViewModel(
    private val drawerDataSource: DemoDrawerDataSource,
    override val drawerStatePresenter: DrawerStatePresenterDefault,
    override val destinationRendersRegistry: DestinationRendersRegistry
) : DrawerViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("DrawerViewModelDefault[${instanceId()}]::onAttach()")
        coroutineScope.launch {
            val childDestinations = drawerDataSource.loadDestinations()
            val navItemDecoNewList = childDestinations.map {
                it.toDrawerNavItem()
            }
            drawerStatePresenter.setNavItemsDeco(navItemDecoNewList)
        }
    }

    override fun onStart() {
        println("DrawerViewModelDefault[${instanceId()}]::onStart()")
    }

    override fun onStop() {
        println("DrawerViewModelDefault[${instanceId()}]::onStop()")
    }

    override fun onDetach() {
        println("DrawerViewModelDefault[${instanceId()}]::onDetach()")
    }

    override fun handleBackPressed() {
    }

}
```
**2-** Once your custom `DrawerViewModel` is defined, then we are going to do the custom `DestinationRender`, in this case lets do a `RootDestinationRender` for simplicity.
Notice bellow how the integration with `Koin` allows us to inject the ViewModel instance and scope it to the nearest NavBackStackEntry.

```Kotlin
class DemoDrawerRootDestinationRender : RootDestinationRender {

    override fun getRoute(): String = ServerUiConstants.Routes.RootGraph.MainEntryPoint

    override fun getRenderType(): String = ServerUiConstants.ComponentType.Drawer

    @Composable
    override fun Content() {

        val viewModel = koinViewModel<DemoDrawerViewModel>()
        DrawerView(viewModel)
    }
}

```
**3-** The last step is registering our implemantation in the DestinationRendersDirectory. For that, in your implementation of `RootGraphInitializer`, place a code similar to this:
```kotlin
class DemoRootGraphInitializer : RootGraphInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<RootDestinationRender> {
        
        // ... Do your initialization stuff and when done, then register all the DestinationRenders in the registry.
        
        val destinationRendersRegistry = koinComponent
            .get<DestinationRendersRegistry>()
            .apply {
                addRoot(DemoDrawerRootDestinationRender())
                add(SimpleScreenDestinationRender())
                add(SimpleScreen1DestinationRender())
            }

        val rootDestinationRender = DemoRootDestinationRender(
            rootComponentJsonResilience,
            destinationRendersRegistry
        )

        return MacaoResult.Success(rootDestinationRender)
    }
}

```
**Above API might improve a little bit by potentially providing the DestinationRendersRegistry as a parameter to the initialize(...) function**

**4-** That was it. Above steps will give you something like shown in the gif image bellow:

<img width="500" src="https://github.com/pablichjenkov/macao-sdk-navigation-compose/assets/5303301/f11e8057-d995-460f-94e1-2b535afaba99">

### BottomNavigation
In Progress ...
