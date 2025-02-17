package app.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    fun onEvent(action: AppAction) {
        when(action) {
            is AppAction.OnThemeChange -> {
                _state.update {
                    it.copy(
                        appTheme = action.theme
                    )
                }
            }
        }
    }

}