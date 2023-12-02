package com.example.cryptotracker.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
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
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
            contentColor = TextColor,
        ),
        onClick = { onClick() }
    ) {
        Text(value)
    }
}

@Composable
fun ButtonDivider(
    text: String
) {
    Row(
        modifier = Modifier
            .padding(0.dp, 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DividerLine()
        SubText(text = text)
        DividerLine()
    }
}

@Composable
private fun RowScope.DividerLine() {
    Divider(
        color = LabelColor,
        modifier = Modifier
            .height(0.5.dp)
            .weight(1f)
            .padding(8.dp, 0.dp)
    )
}

@Composable
fun SignUpButton(
    value: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(0.5.dp, NeonGreen),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = NeonGreen
        )
    ) {
        Text(
            text = value,

            )
    }
}

@Composable
fun ForgotPasswordButton(
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ClickableText(
            modifier = Modifier.padding(0.dp, 8.dp),
            text = AnnotatedString(
                text = value,
            ),
            onClick = { onClick() },
            style = TextStyle(
                color = NeonGreen
            )
        )
    }
}