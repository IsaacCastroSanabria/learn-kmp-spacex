package compose.project.demo.composedemo.data.repository

interface IRocketLaunchesRepository {
    val latestLaunches: Flow<List<RocketLaunch>>
}