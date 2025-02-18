package app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.repository.LocalDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val localDataRepository: LocalDataRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state
        .onStart {
            observeAppThemeStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    fun onEvent(action: AppAction) {
        when (action) {
            is AppAction.OnThemeChange -> {
                viewModelScope.launch {
                    localDataRepository.setAppTheme(action.theme)
                }
            }
        }
    }

    private fun observeAppThemeStatus() {
        localDataRepository
            .getAppTheme()
            .onEach { appTheme ->
                _state.update {
                    it.copy(
                        appTheme = appTheme
                    )
                }
            }.launchIn(viewModelScope)
    }

}
