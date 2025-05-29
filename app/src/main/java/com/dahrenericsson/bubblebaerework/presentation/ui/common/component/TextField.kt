package com.dahrenericsson.bubblebaerework.presentation.ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dahrenericsson.bubblebaerework.R
import com.dahrenericsson.bubblebaerework.presentation.theme.Typography
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

@Composable
private fun BaseTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    validator: Validation<String>? = null,
    validationResult: ValidationResult<String>? = null,
    onValidationResultChange: (ValidationResult<String>?) -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null
) {

    LaunchedEffect(value) {
        onValidationResultChange(
            if (value.isNotEmpty()) validator?.invoke(value) else null
        )
    }

    val hasErrors = validationResult?.errors?.isNotEmpty() == true

    Column {
        Text(text = label, style = Typography.labelMedium)
        TextField(
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = if (hasErrors) Color.Red else Color.Transparent,
                unfocusedIndicatorColor = if (hasErrors) Color.Red else Color.Transparent,
                focusedTextColor = if (hasErrors) Color.Red else Color.Black,
                unfocusedTextColor = if (hasErrors) Color.Red else Color.Black,
            ),
            value = value,
            shape = RoundedCornerShape(12.dp),
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            singleLine = true
        )
        validationResult?.errors?.forEach {
            Text(
                text = it.message,
                color = Color.Red,
                style = Typography.bodySmall
            )
        }
    }
}

@Composable
fun StringTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validationResult: ValidationResult<String>? = null,
    onValidationResultChange: (ValidationResult<String>?) -> Unit = {},
    validator: Validation<String>? = null
) {
    BaseTextField(
        label = label,
        value = value,
        onValueChange = onValueChange,
        validator = validator,
        validationResult = validationResult,
        onValidationResultChange = onValidationResultChange
    )
}

@Composable
fun PasswordTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validationResult: ValidationResult<String>? = null,
    onValidationResultChange: (ValidationResult<String>?) -> Unit = {},
    validator: Validation<String>? = null
) {
    var revealPassword by remember { mutableStateOf(false) }
    val visualTransformation = if (revealPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    BaseTextField(
        label = label,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        validator = validator,
        validationResult = validationResult,
        onValidationResultChange = onValidationResultChange
    ) {
        val icon = if (revealPassword) {
            painterResource(R.drawable.password_hide)
        } else {
            painterResource(R.drawable.password_reveal)
        }

        IconButton(
            onClick = { revealPassword = !revealPassword },
        ) {
            Icon(
                painter = icon,
                contentDescription = "Toggle Password Visibility",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    StringTextField(label = "Username", value = "", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview() {
    PasswordTextField(label = "Password", value = "", onValueChange = {})
}