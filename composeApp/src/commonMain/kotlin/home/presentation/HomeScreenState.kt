package home.presentation

import home.domain.ProjectModel

data class HomeScreenState(
    val isLoading: Boolean = true,
    val message: String = "",
    val error: String = "",
    val projects: List<ProjectModel> = emptyList(),
)
