package compose.project.demo.composedemo.presentation.rocketLaunch

class RocketLaunchViewModel(private val rocketLaunchesRepository: IRocketLaunchesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(RocketLaunchUiState())
    val uiState: StateFlow<RocketLaunchUiState> = _uiState.asStateFlow()

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, launches = emptyList())
            try {
                rocketLaunchesRepository.latestLaunches.collect { launches ->
                    _uiState.value = _uiState.value.copy(isLoading = false, launches = launches)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
}