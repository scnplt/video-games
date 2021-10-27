package dev.sertan.android.videogames.util

import androidx.core.text.HtmlCompat

internal object Converter {

    @JvmStatic
    fun htmlToString(html: String?): String =
        html?.let { HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT).toString() } ?: ""

}
