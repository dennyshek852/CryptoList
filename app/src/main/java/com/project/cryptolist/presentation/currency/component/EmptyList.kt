package com.project.cryptolist.presentation.currency.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.project.cryptolist.presentation.theme.MediumFont
import com.project.cryptolist.presentation.theme.PaddingLarge
import com.project.cryptolist.presentation.theme.PaddingMax
import com.test.cryptolist.R


@Composable
fun EmptyList(
    imageRes: Int,
    title: String,
    subtitle: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = PaddingMax)
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = stringResource(id = R.string.search_empty_image),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .align(Alignment.CenterHorizontally)
                )
                Column(modifier = Modifier.padding(PaddingLarge)) {
                    Text(
                        text = title,
                        color = Color.DarkGray,
                        fontSize = MediumFont,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = MediumFont,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
