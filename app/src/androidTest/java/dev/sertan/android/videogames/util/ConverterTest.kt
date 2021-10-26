package dev.sertan.android.videogames.util

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import org.junit.Test

@SmallTest
internal class ConverterTest {

    @Test
    fun htmlToString_nullInput_returnEmptyResult() {
        val result = Converter.htmlToString(null)
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun htmlToString_htmlInput_returnCorrectResult() {
        val result = Converter.htmlToString("Hello, <b>World</b>")
        Truth.assertThat(result).isEqualTo("Hello, World")
    }

}
