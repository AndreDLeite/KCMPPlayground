package home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import home.domain.usacese.ExampleUseCase
import home.presentation.HomeScreenAction
import home.presentation.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomePageViewModel(private val exampleUseCase: ExampleUseCase) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.OnUseCaseButtonClick -> callUseCase()
            else                                  -> Unit
        }
    }

    private fun callUseCase() {
        viewModelScope.launch {
            val result = exampleUseCase()
            _state.update {
                it.copy(
                    message = result
                )
            }
        }
    }
}