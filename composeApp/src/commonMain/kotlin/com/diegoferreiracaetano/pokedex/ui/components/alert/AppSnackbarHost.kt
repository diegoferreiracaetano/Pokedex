package com.diegoferreiracaetano.pokedex.ui.components.alert

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.extendedColors

enum class SnackbarType {
    ERROR,
    SUCCESS,
    WARNING
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    type: SnackbarType = SnackbarType.ERROR
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier.padding(16.dp),
        snackbar = { data ->
            val (containerColor, contentColor) = when (type) {
                SnackbarType.ERROR -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
                SnackbarType.SUCCESS -> extendedColors.success.color to extendedColors.success.onColor
                SnackbarType.WARNING -> extendedColors.warning.color to extendedColors.warning.onColor
            }

            Snackbar(
                snackbarData = data,
                containerColor = containerColor,
                contentColor = contentColor,
                shape = MaterialTheme.shapes.medium
            )
        }
    )
}
