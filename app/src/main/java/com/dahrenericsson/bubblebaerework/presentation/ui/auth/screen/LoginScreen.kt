package com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.Screen
import com.dahrenericsson.bubblebaerework.presentation.theme.BubbleBaeBackground
import com.dahrenericsson.bubblebaerework.presentation.theme.CrimsonRose
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.viewmodel.LoginViewModel
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.ConfirmationButton
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.PasswordTextField
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.UiStateHandler
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.StringTextField
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.TitleText

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
) {
    BubbleBaeBackground {
        UiStateHandler(
            viewModel = viewModel,
            onSuccess = {
                navHostController.navigate(Screen.Home.route)
            }
        ) {
            LoginContent(onLoginButtonClick = { username, password ->
                viewModel.login(username = username, password = password)
            })
        }
    }
}

@Composable
fun LoginContent(onLoginButtonClick: (String, String) -> Unit = { _, _ -> }) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isButtonEnabled = username.isNotEmpty() && password.isNotEmpty()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.bubblebae_light),
            contentDescription = "Bubblebae Icon",
            modifier = Modifier.size(60.dp)
        )

        TitleText()

        Text(
            text = "LOGIN",
            style = Typography.bodyMedium,
            color = CrimsonRose,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StringTextField(
                label = "Username",
                value = username,
                onValueChange = { username = it },
            )

            PasswordTextField(
                label = "Password",
                onValueChange = { password = it },
                value = password
            )
        }

        ConfirmationButton(
            onClick = {
                onLoginButtonClick(username, password)
            },
            text = "Continue",
            enabled = isButtonEnabled,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginContent()
}

