package com.diegoferreiracaetano.pokedex.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.alert.AppSnackbarHost
import com.diegoferreiracaetano.pokedex.ui.components.loading.AppLoading
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.util.UiState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.login_screen_title


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScaffoldContent(
    modifier: Modifier = Modifier,
    topBar: AppTopBar? = null,
    bottomBar: AppBottomNavigation? = null,
    snackBarHostState: SnackbarHostState,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { AppSnackbarHost(hostState = snackBarHostState) },
        topBar = {
            topBar?.let {
                AppTopBar(
                    title = it.title,
                    onBack = it.onBack,
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar =  {
            bottomBar?.let {
                AppBottomNavigationBar(
                    items = it.items,
                    onTabSelected = it.onTabSelected,
                    selectedRoute = it.selectedRoute
                )
            }
        }
    ) { innerPadding ->
        val baseModifier = modifier
            .widthIn(min = 150.dp, max = 600.dp)
            .fillMaxSize()
            .background(colorScheme.surface)
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)
            .systemBarsPadding()
            .padding(16.dp)

        Box(
            modifier = Modifier
                .background(colorScheme.surface)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            content(baseModifier)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContainer(
    modifier: Modifier = Modifier,
    topBar: AppTopBar? = null,
    bottomBar: AppBottomNavigation? = null,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (Modifier) -> Unit
) {
    AppScaffoldContent(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackBarHostState = snackBarHostState,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppContainer(
    uiState: UiState<T>?,
    modifier: Modifier = Modifier,
    topBar: AppTopBar? = null,
    bottomBar: AppBottomNavigation? = null,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onLoading: @Composable () -> Unit = { AppLoading() },
    content: @Composable (modifier: Modifier, data: T) -> Unit
) {

    LaunchedEffect(uiState?.error) {
        uiState?.error?.let { snackBarHostState.showSnackbar(it) }
    }

    AppScaffoldContent(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackBarHostState = snackBarHostState
    ) { baseModifier ->
        when {
            uiState == null || uiState.isLoading -> onLoading()
            uiState.success != null -> content(baseModifier, uiState.success)
        }
    }
}


@Preview
@Composable
fun AppTopBarPreview() {
    PokedexTheme(true) {
        AppContainer(
            topBar = AppTopBar(title = "Create", {})
        ) { padding->

            Column(
                modifier = padding,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(Res.string.login_screen_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )

            }
        }
    }
}