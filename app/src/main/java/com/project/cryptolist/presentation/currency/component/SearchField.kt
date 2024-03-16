package com.project.cryptolist.presentation.currency.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModel
import com.project.cryptolist.presentation.theme.SmallFont
import com.test.cryptolist.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    currencyListViewModel: CurrencyListViewModel
) {
    //keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    //text field state
    val textFieldValue = remember { mutableStateOf("") }
    val valueIsNotEmpty = textFieldValue.value.isNotEmpty()

    //job
    val coroutineScope = rememberCoroutineScope()
    val cleanSearchJob = {
        coroutineScope.launch {
            textFieldValue.value = ""
            currencyListViewModel.cleanSearch()
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }
    val searchJob: (String) -> Unit = { keywords ->
        coroutineScope.launch {
            textFieldValue.value = keywords
            currencyListViewModel.search(keywords)
        }
    }

    //Search bar ui
    TextField(
        value = textFieldValue.value,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        onValueChange = { searchJob.invoke(it) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(
            textDecoration = TextDecoration.Underline,
            fontSize = SmallFont
        ),
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hints),
                fontSize = SmallFont,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        },
        leadingIcon = if (valueIsNotEmpty) {
            { SearchLeadingIcon { cleanSearchJob.invoke() } }
        } else {
            null
        },
        trailingIcon = {
            SearchTrailingIcon(valueIsNotEmpty) { cleanSearchJob.invoke() }
        }
    )
}
