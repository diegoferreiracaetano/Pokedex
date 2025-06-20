package com.diegoferreiracaetano.pokedex.ui.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.email_message_validation
import pokedex.composeapp.generated.resources.password_message_validation
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.title_password

enum class TextFieldType {
    EMAIL,
    PASSWORD,
    NONE
}
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: StringResource,
    label: StringResource? = null,
    supportingText: StringResource? = null,
    isError: Boolean = false,
    type: TextFieldType = TextFieldType.NONE,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(8.dp)
    val colorScheme = MaterialTheme.colorScheme

    val borderColor = if (isError) colorScheme.error else colorScheme.outline
    val textColor = colorScheme.onSurface
    val placeholderColor = colorScheme.onSurfaceVariant
    val supportingTextColor = if (isError) colorScheme.error else colorScheme.onSurfaceVariant

    val containerColor = colorScheme.surface

    val visualTransformation = when (type) {
        TextFieldType.PASSWORD ->
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        TextFieldType.EMAIL -> VisualTransformation.None
        TextFieldType.NONE -> VisualTransformation.None
    }

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = stringResource(label),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(placeholder),
                    color = placeholderColor
                )
            },
            isError = isError,
            shape = shape,
            visualTransformation = visualTransformation,
            trailingIcon = {
                if (type == TextFieldType.PASSWORD) {
                    val icon =
                        if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = null, tint = textColor)
                    }
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                errorBorderColor = colorScheme.error,
                cursorColor = textColor,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                errorContainerColor = containerColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                errorTextColor = textColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        if (supportingText != null) {
            Text(
                text = stringResource(supportingText),
                color = supportingTextColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp)
            )
        }
    }
}


@Preview()
@Composable
fun AppTextFieldPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    PokedexTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            AppTextField(
                value = email,
                label = Res.string.title_email,
                onValueChange = { email = it },
                placeholder = Res.string.title_email,
                isError = email.contains("cm"),
                type = TextFieldType.EMAIL,
                supportingText = Res.string.email_message_validation
            )

            AppTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = Res.string.title_password,
                isError = password.length < 6,
                type = TextFieldType.PASSWORD,
                supportingText = Res.string.password_message_validation
            )
        }
    }
}
