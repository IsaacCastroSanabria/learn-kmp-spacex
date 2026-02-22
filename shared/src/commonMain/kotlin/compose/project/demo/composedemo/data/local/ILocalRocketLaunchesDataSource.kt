package compose.project.demo.composedemo.data.local

interface ILocalRocketLaunchesDataSource {
    fun getAllLaunches(): List<RocketLaunch>

    fun clearAndCreateLaunches(launches: List<RocketLaunch>)
}