package com.project.cryptolist.presentation.currency.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.test.cryptolist.R

@Composable
fun SearchLeadingIcon(onClick: () -> Unit) {
    IconButton(onClick = { onClick.invoke() })
    {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.arrow_back_icon)
        )
    }
}