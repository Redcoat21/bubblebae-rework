package com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.Screen
import com.dahrenericsson.bubblebaerework.presentation.theme.BubbleBaeBackground
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.ConfirmationButton
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.TitleText

val CollageCircleShape = GenericShape { size, _ ->
    val radius = size.minDimension * 0.40f
    val centerX = size.width / 2
    val centerY = size.height / 2
    addOval(
        Rect(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
    )
}

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    BubbleBaeBackground {
        WelcomeContent(onRegisterTextClick = {
            navHostController.navigate(Screen.Auth.Register.route)
        }, onLoginButtonClick = {
            navHostController.navigate(Screen.Auth.Login.route)
        })
    }
}

@Composable
private fun WelcomeContent(
    onRegisterTextClick: () -> Unit = {},
    onLoginButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(1f)) {
            WelcomeCollage()
        }
        Box {
            TitleText(title = "BubbleBae")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                ConfirmationButton(text = "Login", onClick = onLoginButtonClick)
                Image(
                    painter = painterResource(id = R.drawable.bubblebae_light),
                    contentDescription = "Bubblebae Icon",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = (-15).dp, y = (-12).dp)
                        .zIndex(2f)
                )

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account? ", style = Typography.bodyMedium)
                Text(
                    "Sign up here",
                    color = Flamingo,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onRegisterTextClick()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun WelcomeCollage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Box(
                modifier = Modifier
                    .offset(x = 65.dp, y = 50.dp)
                    .zIndex(2f)
            ) {
                CollagePicture(
                    drawableResource = R.drawable.collage_4,
                    modifier = Modifier
                        .zIndex(2f),
                    imageSize = 150.dp
                )
            }

            Box(modifier = Modifier.offset(x = (-35).dp)) {
                CollagePicture(
                    drawableResource = R.drawable.collage_2,
                    modifier = Modifier
                        .zIndex(0f)
                        .clip(CollageCircleShape),
                    imageSize = 250.dp
                )
            }
        }

        Row {
            Box(modifier = Modifier.offset(x = 20.dp, y = (-130).dp)) {
                CollagePicture(
                    drawableResource = R.drawable.collage_1,
                    imageSize = 250.dp,
                    modifier = Modifier.clip(CollageCircleShape)
                )
            }

            Box(
                modifier = Modifier
                    .offset(x = (-50).dp, y = (-75).dp)
                    .zIndex(2f)
            ) {
                CollagePicture(
                    drawableResource = R.drawable.collage_3,
                    modifier = Modifier,
                )
            }

        }

    }
}

@Composable
private fun CollagePicture(
    modifier: Modifier = Modifier,
    drawableResource: Int,
    contentDescription: String = "",
    alignment: Alignment = Alignment.Center,
    imageSize: Dp = 150.dp,
) {
    Image(
        painter = painterResource(drawableResource),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .zIndex(1f)
            .then(modifier),
        alignment = alignment
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeContent()
}