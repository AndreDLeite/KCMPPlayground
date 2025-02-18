package app.data.datasource

import androidx.datastore.preferences.core.Preferences
import core.domain.enums.AppTheme
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAppTheme(): Flow<Preferences>
    suspend fun setAppTheme(appTheme: AppTheme)
}