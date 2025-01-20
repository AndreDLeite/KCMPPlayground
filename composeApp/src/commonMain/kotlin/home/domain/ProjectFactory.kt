package home.domain

object ProjectFactory {

    fun generateProjectsList(): List<ProjectModel> = listOf(
        ProjectModel(
            name = "E-Commerce",
            lastUpdated = "N/A",
            projectType = ProjectType.ECOMMERCE,
        )
    )
}