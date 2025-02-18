package app.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import core.domain.enums.AppTheme
import core.presentation.theme.APP_THEME_KEY
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val localStoreDataBase: DataStore<Preferences>
) : LocalDataSource {
    override fun getAppTheme(): Flow<Preferences> = localStoreDataBase.data
    override suspend fun setAppTheme(appTheme: AppTheme) {
        val appThemeKey = stringPreferencesKey(APP_THEME_KEY)
        localStoreDataBase.edit {
            it[appThemeKey] = appTheme.name
        }
    }
}
