package e_commerce.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: String,
    val rating: Int,
)
