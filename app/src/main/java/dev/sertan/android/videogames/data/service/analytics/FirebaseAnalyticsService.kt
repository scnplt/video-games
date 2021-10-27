package dev.sertan.android.videogames.data.service.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

internal class FirebaseAnalyticsService : AnalyticsService {
    private val analytics: FirebaseAnalytics by lazy { Firebase.analytics }

    override fun logScreenChange(previousScreen: String, targetScreen: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param("previous_screen", previousScreen)
            param("target_screen", targetScreen)
        }
    }

    override fun logGameFavoriteStatusChange(gameId: Int, gameName: String, favorite: Boolean) {
        analytics.logEvent("game_favorite_status_change") {
            param("game_id", gameId.toString())
            param("game_name", gameName)
            param("is_favorite", favorite.toString())
        }
    }

}
