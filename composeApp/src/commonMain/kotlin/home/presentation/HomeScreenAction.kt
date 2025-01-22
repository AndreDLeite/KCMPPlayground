package home.presentation

import home.domain.ProjectType

sealed interface HomeScreenAction {
    data class OnProjectClick(val project: ProjectType): HomeScreenAction
    data object OnSearchClick: HomeScreenAction
}