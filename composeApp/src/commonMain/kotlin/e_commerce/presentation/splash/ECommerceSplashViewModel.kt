package e_commerce.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ECommerceSplashViewModel: ViewModel() {

    private val _state = Channel<Boolean>()
    val state = _state.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(1500L)
            _state.send(true)
        }
    }

}