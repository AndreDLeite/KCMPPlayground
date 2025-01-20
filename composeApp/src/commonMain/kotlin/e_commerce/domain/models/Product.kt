package e_commerce.domain.models

data class Product(
    val id: String,
    val image: String,
    val name: String,
    val description: String,
    val price: String,
    val rating: Int,
    val latitude: Double,
    val longitude: Double,
)
