package e_commerce.presentation.favorite_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import e_commerce.domain.models.Product
import e_commerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class FavoriteProductsViewModel(
    private val productsRepository: ProductRepository
) : ViewModel() {

    private var currentProducts = mutableListOf<Product>()

    private val _state = MutableStateFlow(FavoriteProductsState())
    val state = _state
        .onStart {
            observeFavoriteProducts()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    fun onAction(action: FavoriteProductsAction) {
        when (action) {
            is FavoriteProductsAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query,
                    )
                }
                observeSearchQuery(action.query)
            }
            else -> Unit
        }

    }

    private fun observeSearchQuery(query: String) {
        val filteredList = currentProducts.filter {
            it.name.contains(
                query.trim(),
                ignoreCase = true
            )
        }
        _state.update {
            it.copy(
                favoriteProducts = filteredList,
            )
        }
    }

    private fun observeFavoriteProducts() {
        productsRepository
            .getFavoriteProducts()
            .onEach { products ->
                currentProducts = products.toMutableList()
                _state.update {
                    it.copy(
                        favoriteProducts = products
                    )
                }
            }.launchIn(viewModelScope)
    }
}
