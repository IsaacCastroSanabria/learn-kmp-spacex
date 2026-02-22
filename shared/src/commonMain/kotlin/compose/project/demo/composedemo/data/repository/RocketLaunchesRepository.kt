package compose.project.demo.composedemo.data.repository

import compose.project.demo.composedemo.data.local.ILocalRocketLaunchesDataSource
import compose.project.demo.composedemo.data.remote.IRemoteRocketLaunchesDataSource
import compose.project.demo.composedemo.data.remote.RocketLaunch
import compose.project.demo.composedemo.domain.IRocketLaunchesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class RocketLaunchesRepository(
    private val localRocketLaunchesDataSource: ILocalRocketLaunchesDataSource,
    private val remoteRocketLaunchesDataSource: IRemoteRocketLaunchesDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) : IRocketLaunchesRepository {

    override val latestLaunches: Flow<List<RocketLaunch>> =
        remoteRocketLaunchesDataSource
            .latestLaunches()
            .onEach { launches ->
                localRocketLaunchesDataSource.clearAndCreateLaunches(launches)
            }
            .flowOn(defaultDispatcher)
            .catch { exception ->
                val cachedLaunches = localRocketLaunchesDataSource.getAllLaunches()
                if (cachedLaunches.isNotEmpty()) {
                    emit(cachedLaunches)
                } else {
                    throw exception
                }
            }
}