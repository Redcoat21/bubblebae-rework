package com.dahrenericsson.bubblebaerework.presentation.ui.common.components

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.theme.CrimsonRose
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography

enum class TitleTextStyle {
    LARGE,
    SMALL
}

@Composable
fun TitleText(title: String = stringResource(R.string.app_name), style: TitleTextStyle = TitleTextStyle.LARGE) {
    when (style) {
        TitleTextStyle.LARGE -> {
            Text(
                text = title,
                style = Typography.titleLarge,
                color = CrimsonRose,
                modifier = Modifier.widthIn(max = 260.dp),
            )
        }

        TitleTextStyle.SMALL -> {
            Text(
                text = title,
                style = Typography.titleSmall,
                color = CrimsonRose,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleTextLargePreview() {
    TitleText("BubbleBae")
}

@Preview(showBackground = true)
@Composable
fun TitleTextSmallPreview() {
    TitleText("BubbleBae", style = TitleTextStyle.SMALL)
}
