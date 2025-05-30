package com.dahrenericsson.bubblebaerework.presentation.ui.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dahrenericsson.bubblebaerework.presentation.theme.CrimsonRose
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography

@Composable
fun TagButton(tag: String, onClick: () -> Unit) {
    var clicked by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }
    var textColor by remember { mutableStateOf(CrimsonRose) }
    LaunchedEffect(clicked) {
        if (clicked) {
            backgroundColor = CrimsonRose
            textColor = Color.White
        } else {
            backgroundColor = Color.Transparent
            textColor = CrimsonRose
        }
    }

    Button(
        onClick = {
            clicked = !clicked
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        border = BorderStroke(2.dp, CrimsonRose),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 7.dp),
        modifier = Modifier.height(35.dp)
    ) {
        Text(text = tag, style = Typography.bodyMedium, color = textColor)
    }

}

@Preview(showBackground = true)
@Composable
fun TagButtonPreview() {
    TagButton(tag = "Hello") { }
}