package com.dahrenericsson.bubblebaerework.presentation.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dahrenericsson.bubblebaerework.presentation.theme.CrimsonRose
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.BubbleCounter
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.TopAppBar

@Composable
fun HomeScreen() {
}

@Composable
fun HomeContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar {
            BubbleCounter(10)
        }

        Text(
            text = "Rent Available",
            style = Typography.titleSmall,
            color = CrimsonRose,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeContent()
}