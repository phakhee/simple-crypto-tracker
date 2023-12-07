package com.example.cryptotracker.ui.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.ui.theme.BackgroundBlack
import com.example.cryptotracker.ui.theme.LabelColor
import com.example.cryptotracker.ui.theme.NeonGreen
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
fun MainHeader(
    text: String
) {
    Text(
        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp),
        text = text,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor
    )
}

@Composable
fun SubHeader(
    text: String
) {
    Text(
        text = text,
        fontSize = 24.sp,
        lineHeight = 24.sp,
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

@Composable
fun ShowToast(text: String, duration: Int) {
    Toast.makeText(LocalContext.current, text, duration).show()
}

@Composable
fun StyledTextField(
    label: String,
    value: String,
    type: String = "",
    icon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .padding(0.dp, 16.dp, 0.dp, 16.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            color = TextColor
        ),
        leadingIcon = { icon?.invoke() },
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                color = LabelColor
            )
        },
        visualTransformation = if (type == "password")
            PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LabelColor,
            focusedBorderColor = TextColor
        )
    )
}

@Composable
private fun StyledCircleIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp),
        color = NeonGreen
    )
}

@Composable
fun ColumnScope.CenteredContent(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun ColumnScope.LoadingIndicator(
    text: String
) {
    CenteredContent {
        StyledCircleIndicator()
        MainText(text = text)
    }
}