package com.project.cryptolist.presentation.currency.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.test.cryptolist.R

@Composable
fun SearchTrailingIcon(valueIsNotEmpty: Boolean, onClick: () -> Unit) {
    if (valueIsNotEmpty) {
        IconButton(onClick = { onClick.invoke() })
        {
            Icon(Icons.Default.Close, contentDescription = stringResource(id = R.string.close_icon))
        }
    } else {
        Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search_icon))
    }
}