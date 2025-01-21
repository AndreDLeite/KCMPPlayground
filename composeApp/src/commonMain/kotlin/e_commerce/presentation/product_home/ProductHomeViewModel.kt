@file:OptIn(FlowPreview::class)

package e_commerce.presentation.product_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.onError
import core.domain.onSuccess
import core.presentation.toUiText
import e_commerce.domain.models.Product
import e_commerce.domain.repository.ProductRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Presentation -> Domain <- Data
class ProductHomeViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var currentProducts = mutableListOf<Product>()

    private val _state = MutableStateFlow(ProductHomeState())
    val state = _state
        .onStart {
            _state.value = _state.value.copy(
                isLoading = true,
            )
            getProducts()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    fun onAction(action: ProductHomeAction) {
        when (action) {
            is ProductHomeAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        isLoading = true,
                        searchQuery = action.query,
                    )
                }
                observeSearchQuery(action.query)
            }

            is ProductHomeAction.OnTabSelected       -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = action.index
                    )
                }
            }

            is ProductHomeAction.OnDrawerClick       -> {
                _state.update {
                    it.copy(
                        shouldOpenDrawer = !it.shouldOpenDrawer
                    )
                }
            }

            is ProductHomeAction.OnRetryClick        -> {
                getProducts()
            }

            else                                     -> Unit
        }
    }

    private fun observeSearchQuery(query: String) {
//        state
//            .map { it.searchQuery }
//            .distinctUntilChanged()
//            .debounce(500L)
//            .onEach { query ->
//                when {
//                    query.isBlank() -> getProducts()
//                    else -> getProducts()
//                }
//            }
//            .launchIn(viewModelScope)
        val filteredList = currentProducts.filter {
            it.name.contains(
                query.trim(),
                ignoreCase = true
            )
        }
        _state.update {
            it.copy(
                isLoading = false,
                products = filteredList,
            )
        }
    }

    private fun getProducts() =
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            productRepository
                .getProducts()
                .onSuccess {
                    currentProducts = it.toMutableList()
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            products = it,
                        )
                    }
                }
                .onError { errorMessage ->
                    _state.update {
                        it.copy(
                            products = emptyList(),
                            isLoading = false,
                            errorMessage = errorMessage.toUiText(),
                        )
                    }
                }
        }


}