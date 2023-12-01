package com.example.cryptotracker.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.ui.theme.LabelColor
import com.example.cryptotracker.ui.theme.NeonGreen
import com.example.cryptotracker.ui.theme.TextColor

@Composable
fun LoginContainer(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}

@Composable
fun FormContainer(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
            .fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun AuthenticationTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            color = TextColor
        ),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                color = LabelColor
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = TextColor,
            focusedBorderColor = TextColor
        )
    )
}

@Composable
fun LoginButton(
    value: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = NeonGreen
        ),
        onClick = { onClick() }
    ) {
        Text(value)
    }
}