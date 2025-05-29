package com.dahrenericsson.bubblebaerework.presentation.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dahrenericsson.bubblebaerework.presentation.Screen
import com.dahrenericsson.bubblebaerework.presentation.theme.BubbleBaeBackground
import com.dahrenericsson.bubblebaerework.presentation.theme.Flamingo
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography
import com.dahrenericsson.bubblebaerework.presentation.ui.auth.viewmodel.RegisterViewModel
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.ConfirmationButton
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.PasswordTextField
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.StringTextField
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.TitleText
import com.dahrenericsson.bubblebaerework.presentation.ui.common.component.UiStateHandler
import com.dahrenericsson.bubblebaerework.presentation.ui.common.validation.CommonValidationRules
import io.konform.validation.ValidationResult

private fun ValidationResult<String>?.isValid() = this?.errors?.isEmpty() == true

/**
 * Terms and Conditions text for the registration screen.
 */
private val termsAndConditionsText = """
    1. ACCEPTANCE OF TERMS
    
    By accessing and using this application, you accept and agree to be bound by the terms and provision of this agreement. In addition, when using this application's particular services, you shall be subject to any posted guidelines or rules applicable to such services.
    
    2. DESCRIPTION OF SERVICE
    
    The application provides users with access to information, services, and various features. Unless explicitly stated otherwise, any new features and/or services added shall be subject to this Terms and Conditions agreement.
    
    3. USER CONDUCT
    
    You are responsible for all activity that occurs under your account. You agree not to use the service for illegal purposes or to engage in activities prohibited by this agreement.
    
    4. PRIVACY POLICY
    
    Registration data and certain other information about you are subject to our Privacy Policy. For more information, please review our full Privacy Policy.
    
    5. THIRD PARTY CONTENT
    
    The application may provide, or third parties may provide, links to other websites or resources. You acknowledge and agree that we are not responsible for the availability of such external sites or resources.
    
    6. MODIFICATION OF TERMS
    
    We reserve the right to modify these terms at any time. We will notify you of any changes by posting the new Terms on the application. Your continued use of the application after such modifications constitutes your acceptance of the modified terms.
    
    7. DISCLAIMER OF WARRANTIES
    
    The service is provided on an "as is" and "as available" basis. We expressly disclaim all warranties of any kind, whether express or implied.
    
    8. LIMITATION OF LIABILITY
    
    Under no circumstances shall we be liable for any direct, indirect, incidental, special, consequential or exemplary damages resulting from your use or inability to use the service.
    
    9. GOVERNING LAW
    
    These Terms shall be governed and construed in accordance with the laws applicable in the jurisdiction where the service is provided, without regard to its conflict of law provisions.
    
    10. CONTACT INFORMATION
    
    If you have any questions about these Terms, please contact us through the application's support channels.
""".trimIndent()

/**
 * Main registration screen that handles state management and navigation.
 */
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    BubbleBaeBackground {
        UiStateHandler(
            viewModel = viewModel,
            onSuccess = { navHostController.navigate(Screen.Auth.Login.route) }
        ) {
            RegisterContent(onRegisterButtonClick = { username, password, email ->
                viewModel.register(username, password, email)
            })
        }
    }
}

/**
 * Main content of the registration screen that handles user input and validation.
 */
@Composable
fun RegisterContent(onRegisterButtonClick: (String, String, String) -> Unit = { _, _, _ -> }) {
    // State declarations
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailValidationResult by remember { mutableStateOf<ValidationResult<String>?>(null) }
    var usernameValidationResult by remember { mutableStateOf<ValidationResult<String>?>(null) }
    var passwordValidationResult by remember { mutableStateOf<ValidationResult<String>?>(null) }
    var confirmPasswordValidationResult by remember { mutableStateOf<ValidationResult<String>?>(null) }

    var termsAndConditionsAccepted by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }
    var termsError by remember { mutableStateOf<String?>(null) }

    // Get the password confirmation validator
    val confirmPasswordValidator = remember(password) {
        CommonValidationRules.confirmPassword(password)
    }

    // Determine if the register button should be enabled
    val isButtonEnabled by remember(
        email, username, password, confirmPassword,
        emailValidationResult, usernameValidationResult,
        passwordValidationResult, confirmPasswordValidationResult,
        termsAndConditionsAccepted
    ) {
        derivedStateOf {
            email.isNotEmpty() &&
            username.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty() &&
            emailValidationResult.isValid() &&
            usernameValidationResult.isValid() &&
            passwordValidationResult.isValid() &&
            confirmPasswordValidationResult.isValid() &&
            termsAndConditionsAccepted
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        TitleText()

        // Form fields
        Column(modifier = Modifier.padding(16.dp)) {
            // Email field
            StringTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                validator = CommonValidationRules.email,
                validationResult = emailValidationResult,
                onValidationResultChange = { emailValidationResult = it }
            )

            // Username field
            StringTextField(
                label = "Username",
                value = username,
                onValueChange = { username = it },
                validator = CommonValidationRules.username,
                validationResult = usernameValidationResult,
                onValidationResultChange = { usernameValidationResult = it }
            )

            // Password field
            PasswordTextField(
                label = "Password",
                value = password,
                onValueChange = {
                    password = it
                    // Re-validate confirm password when password changes
                    if (confirmPassword.isNotEmpty()) {
                        confirmPasswordValidationResult =
                            confirmPasswordValidator.validate(confirmPassword)
                    }
                },
                validator = CommonValidationRules.password,
                validationResult = passwordValidationResult,
                onValidationResultChange = { passwordValidationResult = it }
            )

            // Confirm Password field
            PasswordTextField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                validator = confirmPasswordValidator,
                validationResult = confirmPasswordValidationResult,
                onValidationResultChange = { confirmPasswordValidationResult = it }
            )

            // Terms and Conditions checkbox
            TermsAndConditionsCheckbox(
                checked = termsAndConditionsAccepted,
                errorMessage = termsError,
                onTermsClicked = { showTermsDialog = true },
                onCheckedChange = { checked ->
                    termsAndConditionsAccepted = checked
                    if (checked) termsError = null
                }
            )
        }

        // Register button
        ConfirmationButton(
            text = "Register",
            enabled = isButtonEnabled,
            onClick = {
                if (!termsAndConditionsAccepted) {
                    termsError = "You must accept the Terms and Conditions"
                    return@ConfirmationButton
                }
                onRegisterButtonClick(username, password, email)
            }
        )
    }

    // Terms and Conditions dialog
    if (showTermsDialog) {
        TermsAndConditionsModal(
            text = termsAndConditionsText,
            onAccept = {
                termsAndConditionsAccepted = true
                showTermsDialog = false
            },
            onDecline = {
                termsAndConditionsAccepted = false
                showTermsDialog = false
            },
            onDismiss = { showTermsDialog = false }
        )
    }
}

/**
 * A customized checkbox with a circular shape.
 */
@Composable
private fun RoundCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val size = 20.dp

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = 1.5.dp,
                color = if (checked) MaterialTheme.colorScheme.primary
                       else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                shape = CircleShape
            )
            .background(
                color = if (checked) MaterialTheme.colorScheme.primary
                       else Color.Transparent
            )
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(size * 0.75f)
            )
        }
    }
}

/**
 * Checkbox with terms and conditions text and optional error message.
 */
@Composable
private fun TermsAndConditionsCheckbox(
    checked: Boolean,
    errorMessage: String? = null,
    onTermsClicked: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            RoundCheckBox(checked = checked, onCheckedChange = onCheckedChange)

            Spacer(modifier = Modifier.width(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("I agree to the", style = Typography.bodyMedium)
                Text(
                    text = "Terms and Conditions",
                    color = Flamingo,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable(onClick = onTermsClicked),
                    style = Typography.bodyMedium
                )
            }
        }

        // Show error message if present
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

/**
 * Modal dialog that displays the terms and conditions text.
 */
@Composable
fun TermsAndConditionsModal(
    text: String,
    onAccept: () -> Unit,
    onDecline: () -> Unit,
    onDismiss: () -> Unit,
    showDialog: Boolean = true
) {
    if (!showDialog) return

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Header
                Text(
                    text = "Terms and Conditions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Scrollable content
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = text,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onDecline,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Decline")
                    }

                    Button(
                        onClick = onAccept,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterContent()
}
