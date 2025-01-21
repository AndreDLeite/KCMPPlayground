package e_commerce.data.mappers

import e_commerce.data.database.ProductEntity
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

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        imageUrl = image,
        description = description,
        price = price,
        rating = rating,
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        image = imageUrl,
        description = description,
        price = price,
        rating = rating,
        latitude = 0.0,
        longitude = 0.0,
    )
}