package com.dahrenericsson.bubblebaerework.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.theme.BriceFontFamily
import com.dahrenericsson.bubblebaerework.presentation.theme.CyGroteskFamily
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.OrderButton

@Composable
fun LivestreamCard() {
    Card(modifier = Modifier.size(200.dp)) {
        Box(modifier = Modifier.fillMaxHeight()) {
            // Background image with blur effect applied
// Uncomment the following lines if you want to use a local image resource instead
//            Image(
//                painter = painterResource(R.drawable.collage_1),
//                contentDescription = "Background Image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.blur(radius = 2.dp) // Adding blur effect with 3dp radius
//            )
            AsyncImage(
                model = "",
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.blur(radius = 2.dp) // Adding blur effect with 3dp radius
            )
            Column(modifier = Modifier.padding(10.dp)) {
                LivestreamCardHeader("")
                Spacer(modifier = Modifier.weight(1f))
                LivestreamCardStatusBar()
                LivestreamCardDescription(
                    username = "Streamer Username",
                    bio = "This is a sample bio for the streamer. It can be a bit longer to show how it wraps in the card."
                )
            }
        }
    }
}

@Composable
fun LivestreamCardHeader(talent: Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            model = "https://example.com/image.jpg",
            contentDescription = "Streamer profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(RectangleShape)
        )
// Uncomment the following lines if you want to use a local image resource instead
//        Image(
//            painter = painterResource(R.drawable.collage_1),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(32.dp)
//                .clip(RectangleShape)
//        )
        Column(
            modifier = Modifier.weight(1f) // Give column weight to push ellipsis to the end
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side with name/tags column and time
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Column containing Streamer Name and Tags
                    Column {
                        Text(
                            text = "Streamer Name",
                            fontFamily = BriceFontFamily,
                            color = Color.White,
                            fontSize = 8.sp
                        )

                        // Tags directly under the streamer name in the same column
                        Text(
                            text = "Tag1, Tag2, Tag3",
                            fontFamily = CyGroteskFamily,
                            color = Color.White,
                            fontSize = 6.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    // Time indicator next to the name/tags column
                    Text(
                        text = "1h",
                        fontFamily = CyGroteskFamily,
                        color = Color.White,
                        fontSize = 6.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Top)
                    )
                }

                // Right side with ellipsis button
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ellipsis_icon),
                        contentDescription = "More Menu Icon",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun LivestreamCardStatusBar(
    likeCount: Int = 10000,
    commentCount: Int = 3500,
    onOrderClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.height(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.heart_icon),
                contentDescription = "Like Icon",
                modifier = Modifier.size(10.dp),
                tint = Flamingo
            )
            Text(
                text = likeCount.toFormattedString(),
                fontSize = 8.sp,
                color = Color.White,
                fontFamily = CyGroteskFamily
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.comment_icon),
                contentDescription = "Comment Icon",
                modifier = Modifier.size(10.dp),
                tint = Flamingo
            )
            Text(
                text = commentCount.toFormattedString(),
                fontSize = 8.sp,
                color = Color.White,
                fontFamily = CyGroteskFamily
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        // Use the small version of OrderButton to match height with icons
        OrderButton(
            text = "Order",
            onOrderClick = onOrderClick,
        )
    }
}

@Composable
fun LivestreamCardDescription(username: String, bio: String) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$username • ")
        }
        append(bio)
    }
    Text(
        text = annotatedText,
        color = Color.White,
        fontSize = 8.sp,
        fontFamily = CyGroteskFamily,
    )
}

private fun Int.toFormattedString(): String {
    return when {
        this >= 1_000_000 -> "${this / 1_000_000}.${this % 1_000_000 / 100_000}M"
        this >= 1_000 -> "${this / 1_000}.${this % 1_000 / 100}K"
        else -> this.toString()
    }
}

@Preview
@Composable
fun LivestreamCardPreview() {
    LivestreamCard()
}

