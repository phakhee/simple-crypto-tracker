package com.example.cryptotracker.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.ui.theme.BackgroundBlack
import com.example.cryptotracker.ui.theme.LabelColor
import com.example.cryptotracker.ui.theme.TextColor

@Composable
fun AppBox(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlack),
    ) {
        content()
    }
}

@Composable
fun ColumnScope.ColumnSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun ActivityHeader(
    text: String
) {
    Text(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 32.dp),
        text = text,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor
    )
}

@Composable
fun MainText(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = TextColor
    )
}

@Composable
fun SubText(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = LabelColor
    )
}