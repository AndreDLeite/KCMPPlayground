package e_commerce.data.mappers

import e_commerce.data.dto.ProductDto
import e_commerce.domain.models.Product

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        image = image,
        name = name,
        description = description,
        price = price,
        rating = rating,
        latitude = latitude,
        longitude = longitude,
    )
}
