package e_commerce.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoriteProductDatabase> {
        val appContext = context.applicationContext

        val dbFile = appContext.getDatabasePath(FavoriteProductDatabase.DATABASE_NAME)

        return Room.databaseBuilder(
            context = appContext,
            dbFile.absolutePath,
        )
    }
}