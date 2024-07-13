package ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usacese.ExampleUseCase
import kotlinx.coroutines.launch

class HomePageViewModel(private val exampleUseCase: ExampleUseCase) : ViewModel() {

    private val _message = mutableStateOf("")
    val message = _message

    fun callUseCase() {
        viewModelScope.launch {
            _message.value = exampleUseCase()
        }
    }
}