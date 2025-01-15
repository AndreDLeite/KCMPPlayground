package home.presentation

data class HomeScreenState(
    val isLoading: Boolean = true,
    val message: String = "",
    val error: String = "",
)
