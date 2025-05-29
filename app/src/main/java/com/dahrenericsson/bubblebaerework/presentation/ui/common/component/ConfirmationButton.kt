package com.dahrenericsson.bubblebaerework.presentation.ui.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography

@Composable
fun ConfirmationButton(
    onClick: () -> Unit = {},
    text: String,
    enabled: Boolean = true,
    buttonColor: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Flamingo,
        contentColor = Color.White
    )
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = buttonColor,
        modifier = Modifier.fillMaxWidth(0.8f).padding(0.dp)
    ) {
        Text(text = text, style = Typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationButtonPreview() {
    ConfirmationButton(text = "Register")
}

