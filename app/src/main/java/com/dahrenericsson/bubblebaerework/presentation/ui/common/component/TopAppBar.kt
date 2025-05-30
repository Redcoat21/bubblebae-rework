package com.dahrenericsson.bubblebaerework.presentation.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.theme.BriceFontFamily
import com.dahrenericsson.bubblebaerework.presentation.theme.CrimsonRose
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo

@Composable
fun TopAppBar(content: @Composable () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.bubblebae_dark),
            contentDescription = "BubbleBae Logo",
            modifier = Modifier.size(24.dp)
        )

        Box(modifier = Modifier.weight(1f)) {
            TitleText(style = TitleTextStyle.SMALL)
        }

        content()
    }
}

@Composable
fun BubbleCounter(bubbleCount: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Flamingo)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.bubble_gift),
            contentDescription = "Bubble Counter",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = bubbleCount.toString(),
            fontFamily = BriceFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 9.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}

@Preview(showBackground = true)
@Composable
fun TopAppBarWithBubbleCounterPreview() {
    TopAppBar {
        BubbleCounter(bubbleCount = 42)
    }
}