package com.example.cryptotracker.ui.composables

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.cryptotracker.ui.classes.Crypto
import com.example.cryptotracker.ui.theme.ContentBackground
import com.example.cryptotracker.ui.theme.NeonGreen

@Composable
fun DashboardContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        content()
    }
}

@Composable
fun CryptoDropdownIcon(
    url: String,
    description: String
) {
    AsyncImage(
        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
        model = url,
        contentDescription = description
    )
}

@Composable
fun CryptoDropdownMenu(
    expanded: Boolean,
    content: @Composable () -> Unit
) {

    Column {
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(ContentBackground),
            expanded = expanded,
            onDismissRequest = { },
            properties = PopupProperties(focusable = false)
        ) {
            content()
        }
    }
}

@Composable
fun CryptoDropdownItem(
    text: String,
    subText: String = "",
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Row {
                MainText(text = text)
                Spacer(modifier = Modifier.width(4.dp))
                SubText(text = subText)
            }
        },
        onClick = { onClick() }
    )
}
