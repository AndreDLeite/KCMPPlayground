package home.presentation

sealed interface HomeScreenAction {
    data object OnBackButtonClick: HomeScreenAction
    data object OnUseCaseButtonClick: HomeScreenAction
}