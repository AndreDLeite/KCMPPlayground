package home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import home.domain.ProjectFactory
import home.domain.usacese.ExampleUseCase
import home.presentation.HomeScreenAction
import home.presentation.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state
        .onStart {
            _state.value = _state.value.copy(
                isLoading = true,
            )
            getProjects()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    private fun getProjects() {
        viewModelScope.launch {
            val result = ProjectFactory.generateProjectsList()
            _state.update {
                it.copy(
                    isLoading = false,
                    projects = result
                )
            }
        }
    }
}