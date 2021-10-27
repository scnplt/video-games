package dev.sertan.android.videogames.util

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import org.junit.Test

@SmallTest
internal class ConverterTest {

    @Test
    fun htmlToString_nullInput_returnEmptyResult() {
        val response = Converter.htmlToString(null)
        Truth.assertThat(response).isEmpty()
    }

    @Test
    fun htmlToString_htmlInput_returnCorrectResult() {
        val response = Converter.htmlToString("Hello, <b>World</b>")
        Truth.assertThat(response).isEqualTo("Hello, World")
    }

}
