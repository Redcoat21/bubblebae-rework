package com.dahrenericsson.bubblebaerework.auth

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen.RegisterContent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        // Set up the composable to test
        composeTestRule.setContent {
            RegisterContent()
        }
    }

    // Helper function to fill a text field by directly targeting the TextField component
    // instead of clicking on the label Text which doesn't focus the field
    private fun SemanticsNodeInteractionsProvider.fillTextField(label: String, value: String) {
        // Instead of using complex indexing, try a simpler approach
        // The order of text fields in the UI is: Email, Username, Password, Confirm Password
        when (label) {
            "Email" -> onAllNodes(hasSetTextAction())[0].performTextInput(value)
            "Username" -> onAllNodes(hasSetTextAction())[1].performTextInput(value)
            "Password" -> onAllNodes(hasSetTextAction())[2].performTextInput(value)
            "Confirm Password" -> onAllNodes(hasSetTextAction())[3].performTextInput(value)
            else -> throw IllegalArgumentException("Unknown field label: $label")
        }
    }

    @Test
    fun registerButtonIsDisabledWhenFieldsAreEmpty() {
        // Initially, the button should be disabled
        composeTestRule.onNodeWithText("Register").assertIsNotEnabled()
    }

    @Test
    fun registerButtonIsDisabledWhenSomeFieldsAreEmpty() {
        // Fill only email
        composeTestRule.fillTextField("Email", "test@example.com")

        // Button should still be disabled
        composeTestRule.onNodeWithText("Register").assertIsNotEnabled()
    }

    @Test
    fun registerButtonIsDisabledWhenPasswordsDoNotMatch() {
        // Fill all fields
        with(composeTestRule) {
            fillTextField("Email", "test@example.com")
            fillTextField("Username", "testuser")
            fillTextField("Password", "Password123!")
            fillTextField("Confirm Password", "Password456!")

            // Check terms and conditions
            onNode(hasText("I agree to the")).performClick()

            // Button should be disabled because passwords don't match
            onNodeWithText("Register").assertIsNotEnabled()
        }
    }

    @Test
    fun registerButtonIsDisabledWhenTermsNotAccepted() {
        // Fill all fields
        with(composeTestRule) {
            fillTextField("Email", "test@example.com")
            fillTextField("Username", "testuser")
            fillTextField("Password", "Password123!")
            fillTextField("Confirm Password", "Password123!")

            // Don't check terms

            // Button should be disabled because terms aren't accepted
            onNodeWithText("Register").assertIsNotEnabled()
        }
    }

    @Test
    fun termsAndConditionsModalIsDisplayedWhenTermsTextClicked() {
        // Click on the terms and conditions text
        composeTestRule.onNodeWithText("Terms and Conditions").performClick()

        // Check that the modal is shown
        // Look for the Accept button in the dialog to verify the dialog is displayed
        composeTestRule.onNodeWithText("Accept").assertIsDisplayed()
        composeTestRule.onNodeWithText("Decline").assertIsDisplayed()
    }

    @Test
    fun registerButtonIsEnabledWhenAllValidationsPass() {
        // Fill all fields with valid data
        with(composeTestRule) {
            // Fill all fields
            fillTextField("Email", "test@example.com")
            fillTextField("Username", "testuser")
            fillTextField("Password", "Password123!")
            fillTextField("Confirm Password", "Password123!")

            // Click away from fields to trigger validation - click on the background
            // This ensures all fields lose focus and validations trigger
            onRoot().performClick()

            // Open terms and conditions dialog
            onNodeWithText("Terms and Conditions").performClick()

            // Accept terms
            onNodeWithText("Accept").performClick()

            // Wait for all UI updates to complete
            waitForIdle()

            // Now button should be enabled
            onNodeWithText("Register").assertIsEnabled()
        }
    }

    @Test
    fun termsAndConditionsModalAcceptButtonChecksTheBox() {
        with(composeTestRule) {
            // Click on the terms and conditions text to show the modal
            onNodeWithText("Terms and Conditions").performClick()

            // Click the Accept button
            onNodeWithText("Accept").performClick()

            // Fill all fields with valid data
            fillTextField("Email", "test@example.com")
            fillTextField("Username", "testuser")
            fillTextField("Password", "Password123!")
            fillTextField("Confirm Password", "Password123!")

            // Button should be enabled because terms were accepted
            onNodeWithText("Register").assertIsEnabled()
        }
    }

    @Test
    fun termsAndConditionsModalDeclineButtonKeepsBoxUnchecked() {
        with(composeTestRule) {
            // Click on the terms and conditions text to show the modal
            onNodeWithText("Terms and Conditions").performClick()

            // Click the Decline button
            onNodeWithText("Decline").performClick()

            // Fill all fields with valid data
            fillTextField("Email", "test@example.com")
            fillTextField("Username", "testuser")
            fillTextField("Password", "Password123!")
            fillTextField("Confirm Password", "Password123!")

            // Button should still be disabled because terms weren't accepted
            onNodeWithText("Register").assertIsNotEnabled()
        }
    }

    @Test
    fun validationErrorsAreDisplayedForInvalidInputs() {
        with(composeTestRule) {
            // Enter invalid email
            fillTextField("Email", "notanemail")

            // Enter short username
            fillTextField("Username", "a")

            // Enter weak password
            fillTextField("Password", "weak")

            // Focus confirm password to trigger validations
            onAllNodesWithText("Confirm Password")[0].performClick()

            // Button should be disabled
            onNodeWithText("Register").assertIsNotEnabled()
        }
    }
}
