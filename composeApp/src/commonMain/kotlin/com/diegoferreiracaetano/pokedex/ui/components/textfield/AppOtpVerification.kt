package com.diegoferreiracaetano.pokedex.ui.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.otp_error_default
import pokedex.composeapp.generated.resources.otp_resend_in
import pokedex.composeapp.generated.resources.otp_resend_now
import pokedex.composeapp.generated.resources.otp_resend_prompt

@Composable
fun AppOtpVerification(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    isError: Boolean = false,
    errorText: String? = null,
    onOtpTextChange: (text: String, isComplete: Boolean) -> Unit,
    onResendClick: () -> Unit
) {
    var countdown by remember { mutableStateOf(59) }
    var isTimerRunning by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isTimerRunning) {
        if (isTimerRunning) {
            while (countdown > 0) {
                delay(1000)
                countdown--
            }
            isTimerRunning = false
        }
    }

    val restartTimer = {
        countdown = 59
        isTimerRunning = true
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
            onValueChange = {
                if (it.text.length <= otpCount) {
                    onOtpTextChange(it.text, it.text.length == otpCount)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            ),
            visualTransformation = VisualTransformation.None,
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(otpCount) { index ->
                        CharView(index = index, text = otpText, isError = isError)
                        if (index < otpCount - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        )

        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.otp_resend_prompt),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (isTimerRunning) {

                val formattedCountDown = countdown.toString().padStart(2, '0')

                Text(
                    text = stringResource(Res.string.otp_resend_in, formattedCountDown),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = stringResource(Res.string.otp_resend_now),
                    modifier = Modifier.clickable {
                        onResendClick()
                        restartTimer()
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isError: Boolean
) {
    val isFocused = text.length == index
    val char = if (index < text.length) text[index].toString() else ""
    val shape = RoundedCornerShape(8.dp)
    val colorScheme = MaterialTheme.colorScheme

    val borderColor = when {
        isFocused -> colorScheme.primary
        isError -> colorScheme.error
        else -> colorScheme.outline
    }

    Box(
        modifier = Modifier
            .width(50.dp)
            .height(56.dp)
            .border(1.dp, borderColor, shape)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            style = MaterialTheme.typography.headlineSmall,
            color = colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun AppOtpVerificationPreview() {
    PokedexTheme {
        var otpValue by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AppOtpVerification(
                otpText = otpValue,
                isError = isError,
                errorText = if (isError) stringResource(Res.string.otp_error_default) else null,
                onOtpTextChange = { text, isComplete ->
                    otpValue = text
                    isError = isComplete && text != "123456"
                },
                onResendClick = {
                    otpValue = ""
                    isError = false
                }
            )
        }
    }
}
