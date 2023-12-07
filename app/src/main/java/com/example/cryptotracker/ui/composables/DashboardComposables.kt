package com.example.cryptotracker.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.cryptotracker.ui.theme.ContentBackground
import com.example.cryptotracker.ui.theme.LabelColor
import com.example.cryptotracker.ui.theme.NeonGreen
import com.example.cryptotracker.ui.theme.NeonRed
import com.example.cryptotracker.ui.theme.TextColor
import com.example.cryptotracker.ui.theme.UnselectedGreen

@Composable
fun DashboardContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp, 0.dp)
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

@Composable
fun PriceText(
    text: String
) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor
    )
}

@Composable
fun PriceChange(
    value: Double
) {
    Text(
        text = "${value}%",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = if (value < 0) NeonRed else NeonGreen
    )
}

@Composable
fun MetaDataColumn(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp, 0.dp, 32.dp)
    ) {
        content()
    }
}

@Composable
fun QuoteRow(
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 8.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun RowScope.QuoteData(
    fill: Float = 1f,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .weight(fill)
            .background(ContentBackground)
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun QuoteDataName(
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
fun QuoteDataValue(
    text: String
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
        selected = selected,
        onClick = { onClick() },
        label = { MainText(text = text) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = UnselectedGreen,
            selectedContainerColor = NeonGreen
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = UnselectedGreen,
            selectedBorderColor = NeonGreen
        )
    )
}