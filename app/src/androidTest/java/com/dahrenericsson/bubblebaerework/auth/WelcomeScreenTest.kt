package com.dahrenericsson.bubblebaerework.auth

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen.WelcomeContent
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `login button should trigger login navigation`() {
        // Track if login was triggered
        var loginClicked = false

        // Set up the composable to test with navigation callback
        composeTestRule.setContent {
            WelcomeContent(
                onLoginButtonClick = { loginClicked = true }
            )
        }

        // Click the login button
        composeTestRule.onNodeWithText("Login").performClick()

        // Assert that the callback was invoked
        assertTrue("Login button click callback wasn't triggered", loginClicked)
    }

    @Test
    fun `register text should trigger register navigation`() {
        // Track if register was triggered
        var registerClicked = false

        // Set up the composable to test with navigation callback
        composeTestRule.setContent {
            WelcomeContent(
                onRegisterTextClick = { registerClicked = true }
            )
        }

        // Click the register text
        composeTestRule.onNodeWithText("Sign up here").performClick()

        // Assert that the callback was invoked
        assertTrue("Register text click callback wasn't triggered", registerClicked)
    }
}
