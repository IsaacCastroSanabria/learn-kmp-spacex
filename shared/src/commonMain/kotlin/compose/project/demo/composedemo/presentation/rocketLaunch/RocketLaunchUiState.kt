package compose.project.demo.composedemo.presentation.rocketLaunch

data class RocketLaunchUiState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)