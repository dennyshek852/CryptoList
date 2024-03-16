package com.project.cryptolist.presentation.currency.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.project.cryptolist.presentation.theme.LetterIconRadius
import com.project.cryptolist.presentation.theme.LetterIconSize
import com.project.cryptolist.presentation.theme.MediumFont
import com.project.cryptolist.presentation.theme.PaddingLarge
import java.util.Locale


@Composable
fun LetterIcon(letter: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(end = PaddingLarge)
            .size(LetterIconSize)
            .clip(RoundedCornerShape(LetterIconRadius))
            .background(Color.DarkGray)
    ) {
        Text(
            text = letter.uppercase(Locale.ROOT),
            color = Color.White,
            fontSize = MediumFont,
            textAlign = TextAlign.Center,
        )
    }
}
