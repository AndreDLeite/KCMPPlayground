package app.domain.repository

import core.domain.enums.AppTheme
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {
    fun getAppTheme(): Flow<AppTheme>
    suspend fun setAppTheme(theme: AppTheme)
}