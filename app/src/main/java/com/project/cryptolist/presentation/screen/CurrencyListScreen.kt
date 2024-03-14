package com.project.cryptolist.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.cryptolist.domain.model.CurrencyDisplayModel
import com.project.cryptolist.presentation.currency.viewmodel.CurrencyListViewModelImpl
import com.test.cryptolist.R
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun CurrencyListScreen(
    currencyListViewModel: CurrencyListViewModelImpl
) {
    // you can collect the state with the `collectAsState` function and use it in your Compose UI.
    val displayList by currencyListViewModel.displayList.collectAsState()
    val initialTextFieldValue = TextFieldValue("")
    //text field state
    val textFieldValue = remember { mutableStateOf(initialTextFieldValue) }
    Column {
        //Search field
        SearchField(textFieldValue, currencyListViewModel)
        //Divider
        Divider(thickness = 0.5.dp, color = Color.LightGray)
        //Currency List
        CurrencyList(displayList = displayList)
    }
}

@Composable
fun CurrencyList(displayList: List<CurrencyDisplayModel>) {
    if (displayList.isEmpty()) {
        EmptyList(
            imageRes = R.drawable.search_fail,
            title = stringResource(R.string.empty_list_title),
            subtitle = stringResource(id = R.string.empty_list_subtitle)
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(displayList.size) { index ->
                val id = displayList[index].id
                val leading = displayList[index].name.firstOrNull()?.toString() ?: ""
                ListItem(
                    modifier = Modifier.fillMaxHeight(),
                    leadingContent = { LetterIcon(leading) },
                    headlineContent = {
                        Text(
                            modifier = Modifier.fillMaxHeight(),
                            text = displayList[index].name,
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                        Divider(thickness = 0.5.dp, color = Color.LightGray)
                    },
                    trailingContent = {
                        Row(
                            modifier = Modifier.offset(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = id,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(end = 8.dp),
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Right Arrow Icon",
                                tint = Color.Gray
                            )
                        }
                    },
                )

            }
        }
    }
}

@Composable
fun LetterIcon(letter: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.DarkGray)
    ) {
        Text(
            text = letter.uppercase(Locale.ROOT),
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    textFieldValue: MutableState<TextFieldValue>,
    currencyListViewModel: CurrencyListViewModelImpl
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val valueIsNotEmpty = textFieldValue.value.text.isNotEmpty()
    val coroutineScope = rememberCoroutineScope()
    val cleanSearchJob = {
        textFieldValue.value = TextFieldValue("")
        coroutineScope.launch {
            currencyListViewModel.cleanSearch()
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }
    val searchJob: (TextFieldValue) -> Unit = { keywords ->
        coroutineScope.launch {
            textFieldValue.value = keywords
            currencyListViewModel.search(keywords.text)
        }
    }

    TextField(
        value = textFieldValue.value,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        onValueChange = { searchJob.invoke(it) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(textDecoration = TextDecoration.Underline),
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hints),
                fontSize = 14.sp,
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

@Composable
fun SearchTrailingIcon(valueIsNotEmpty: Boolean, onClick: () -> Unit) {
    if (valueIsNotEmpty) {
        IconButton(onClick = { onClick.invoke() })
        {
            Icon(Icons.Default.Close, contentDescription = "close icon")
        }
    } else {
        Icon(Icons.Default.Search, contentDescription = "search icon")
    }
}

@Composable
fun SearchLeadingIcon(onClick: () -> Unit) {
    IconButton(onClick = { onClick.invoke() })
    {
        Icon(Icons.Default.ArrowBack, contentDescription = "arrow back icon")
    }
}

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
                .padding(top = 40.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "search empty image",
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
