package com.dahrenericsson.bubblebaerework.presentation.ui.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo

@Composable
fun OrderButton(
    text: String = "Order",
    onOrderClick: () -> Unit = {},
) {
    Button(
        onClick = onOrderClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Flamingo,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.height(10.dp)
    ) {
        Text(
            text = text,
            fontSize = 6.sp,
            lineHeight = 6.sp
        )
    }
}

@Preview
@Composable
fun OrderButtonPreview() {
    OrderButton()
}

