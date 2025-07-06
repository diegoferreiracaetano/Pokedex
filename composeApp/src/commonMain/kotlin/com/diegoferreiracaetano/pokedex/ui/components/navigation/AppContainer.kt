package com.diegoferreiracaetano.pokedex.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { AppSnackbarHost(hostState = snackBarHostState) },
        topBar = {
            topBar?.let {
                AppTopBarFactory(
                    config = it,
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar = {
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
         //   .background(MaterialTheme.colorScheme.surface)
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)
            .systemBarsPadding()
            .padding(16.dp)

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
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
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
    content: @Composable (Modifier) -> Unit
) {
    AppScaffoldContent(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackBarHostState = snackBarHostState,
        scrollBehavior = scrollBehavior,
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
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
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
        snackBarHostState = snackBarHostState,
        scrollBehavior = scrollBehavior
    ) { baseModifier ->
        when {
            uiState == null || uiState.isLoading -> onLoading()
            uiState.success != null -> content(baseModifier, uiState.success)
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBarPreview() {
    PokedexTheme {
        AppContainer(
            topBar = AppTopBar(
                title = "Create",
                backgroundColor = MaterialTheme.colorScheme.background,
                onBack = {}
            )
        ) { padding ->
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
