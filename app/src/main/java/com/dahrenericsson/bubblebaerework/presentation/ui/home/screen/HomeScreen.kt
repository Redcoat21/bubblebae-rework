package com.dahrenericsson.bubblebaerework.presentation.ui.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.dahrenericsson.bubblebaerework.presentation.ui.home.component.TagButton

@Composable
fun HomeScreen() {
}

@Composable
fun HomeContent(tags: List<String>) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
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

        TagsList(tags)
    }
}

@Composable
fun TagsList(tags: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(tags) {
            TagButton(it) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeContent(tags = listOf<String>("Hello", "World"))
}