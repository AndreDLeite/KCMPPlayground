package e_commerce.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteProductDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ECommerce")
            os.contains("mac") -> File(userHome, "Library/Application Support/ECommerce")
            else -> File(userHome, ".local/share/ECommerce")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavoriteProductDatabase.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}