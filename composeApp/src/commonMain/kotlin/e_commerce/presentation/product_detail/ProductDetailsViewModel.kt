package e_commerce.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.onError
import core.domain.onSuccess
import core.presentation.toUiText
import e_commerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: String,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailState())
    val state = _state
        .onStart {
            getProductInfo()
            observeFavoritesStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    private fun getProductInfo() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            productRepository
                .getProductById(productId)
                .onSuccess { product ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            currentProduct = product,
                            errorMessage = null,
                        )
                    }
                }
                .onError { errorMessage ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            currentProduct = null,
                            errorMessage = errorMessage.toUiText()
                        )
                    }
                }
        }
    }

    fun onAction(action: ProductDetailAction) {
        when (action) {
            is ProductDetailAction.OnAddToFavoritesClick -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        productRepository.deleteFromFavorites(productId)
                    } else {
                        state.value.currentProduct?.let { product ->
                            productRepository.markAsFavorites(product)
                        }
                    }
                }
            }

            is ProductDetailAction.OnRetryClick          -> {
                getProductInfo()
            }

            else                                         -> Unit
        }
    }

    private fun observeFavoritesStatus() {
        productRepository
            .isProductFavorite(productId)
            .onEach { isFavorite ->
                _state.update {
                    it.copy(
                        isFavorite = isFavorite
                    )
                }
            }.launchIn(viewModelScope)
    }

}