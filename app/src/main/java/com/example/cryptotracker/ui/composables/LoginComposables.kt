package com.example.cryptotracker.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
            .padding(0.dp, 0.dp, 0.dp, 32.dp)
            .fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun AuthenticationTextField(
    label: String,
    value: String,
    type: String = "",
    onValueChange: (String) -> Unit,
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
        visualTransformation = if (type == "password")
            PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LabelColor,
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
            containerColor = NeonGreen,
            contentColor = TextColor
        ),
        onClick = { onClick() }
    ) {
        Text(value)
    }
}