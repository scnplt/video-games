package dev.sertan.android.videogames.data.service.analytics

internal interface AnalyticsService {
    fun logScreenChange(previousScreen: String, targetScreen: String)
    fun logGameFavoriteStatusChange(gameName: String, favorite: Boolean)
}
