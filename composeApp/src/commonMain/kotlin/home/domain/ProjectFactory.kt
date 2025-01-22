package home.domain

object ProjectFactory {

    fun generateProjectsList(): List<ProjectModel> = listOf(
        ProjectModel(
            name = "E-Commerce",
            lastUpdated = "Base project for a mockup E-Commerce",
            projectType = ProjectType.ECOMMERCE,
        )
    )
}