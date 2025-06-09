package com.diegoferreiracaetano.pokedex.ui.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.diegoferreiracaetano.pokedex.ui.components.alert.AppSnackbarHost
import org.jetbrains.compose.resources.stringResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    string: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable (Modifier) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { snackbarHostState?.let {
            AppSnackbarHost(hostState = it)
        } },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        string,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.action_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->

        content(modifier
            .padding(innerPadding))
    }
}