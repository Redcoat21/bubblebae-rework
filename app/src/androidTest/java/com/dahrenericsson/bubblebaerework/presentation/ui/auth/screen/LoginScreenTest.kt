package com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginButton_isDisabled_whenFieldsAreEmpty() {
        // Set up the composable to test
        composeTestRule.setContent {
            LoginContent()
        }

        // Check that the button is disabled when fields are empty
        composeTestRule.onNodeWithText("Continue").assertIsNotEnabled()
    }

    @Test
    fun loginButton_isEnabled_whenFieldsAreFilled() {
        // Set up the composable to test
        composeTestRule.setContent {
            LoginContent()
        }

        // Find and interact with text fields using more specific selectors
        // Target the actual input fields that have text input actions
        composeTestRule
            .onAllNodes(hasSetTextAction())[0]  // First text field (Username)
            .performTextInput("testuser")

        composeTestRule
            .onAllNodes(hasSetTextAction())[1]  // Second text field (Password)
            .performTextInput("password")

        // Now the button should be enabled
        composeTestRule.onNodeWithText("Continue").assertIsEnabled()
    }
}

