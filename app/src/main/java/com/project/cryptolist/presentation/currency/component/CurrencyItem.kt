package com.project.cryptolist.presentation.currency.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.presentation.theme.DividerHeight
import com.project.cryptolist.presentation.theme.MediumFont
import com.project.cryptolist.presentation.theme.PaddingLarge
import com.test.cryptolist.R

@Composable
fun CurrencyItem(currencyDisplayModel: CurrencyDisplayModel) {
    val leadingLetter = currencyDisplayModel.name.firstOrNull()?.toString() ?: ""
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = PaddingLarge),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        //Leading
        LetterIcon(leadingLetter)
        Column {
            Row(modifier = Modifier.padding(vertical = PaddingLarge)) {
                //Headline
                Text(
                    modifier = Modifier.weight(1f),
                    text = currencyDisplayModel.name,
                    fontSize = MediumFont,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
                //Trailing
                Row(modifier = Modifier.padding(end = PaddingLarge)) {
                    Text(
                        text = currencyDisplayModel.id,
                        fontSize = MediumFont,
                        modifier = Modifier.padding(end = PaddingLarge),
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(id = R.string.arrow_right_icon),
                        tint = Color.Gray
                    )
                }
            }
            Divider(thickness = DividerHeight, color = Color.LightGray)
        }
    }
}
