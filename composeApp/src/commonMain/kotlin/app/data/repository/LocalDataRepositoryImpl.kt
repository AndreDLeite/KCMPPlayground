package app.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import app.data.datasource.LocalDataSource
import app.data.mappers.toAppTheme
import app.domain.repository.LocalDataRepository
import core.domain.enums.AppTheme
import core.presentation.APP_THEME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalDataRepository {
    override fun getAppTheme(): Flow<AppTheme> {
        val appThemeKey = stringPreferencesKey(APP_THEME_KEY)
        return localDataSource.getAppTheme().map {
            it[appThemeKey]?.toAppTheme() ?: AppTheme.GoldenYellow
        }
    }

    override suspend fun setAppTheme(theme: AppTheme) {
        localDataSource.setAppTheme(theme)
    }

}
