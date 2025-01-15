package e_commerce.presentation.product_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ProductHomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProductHomeState())
    val state = _state
        .onStart {
            _state.value = _state.value.copy(
                isLoading = true,
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    fun onAction(action: ProductHomeAction) {
        when(action) {
            is ProductHomeAction.OnProductClick      -> TODO()
            is ProductHomeAction.OnSearchQueryChange -> TODO()
            is ProductHomeAction.OnTabSelected       -> TODO()
            is ProductHomeAction.OnDrawerClick       -> {
                _state.update {
                    it.copy(
                        shouldOpenDrawer = !it.shouldOpenDrawer
                    )
                }
            }
            else -> Unit
        }
    }

}