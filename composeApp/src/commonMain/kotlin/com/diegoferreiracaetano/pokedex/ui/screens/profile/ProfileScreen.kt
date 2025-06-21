package com.diegoferreiracaetano.pokedex.ui.screens.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diegoferreiracaetano.pokedex.ui.components.list.GuestProfileHeader
import com.diegoferreiracaetano.pokedex.ui.components.list.LoggedProfileHeader
import com.diegoferreiracaetano.pokedex.ui.components.list.SectionTitle
import com.diegoferreiracaetano.pokedex.ui.components.list.TextRow
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppBottomNavigation
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.language_game_info
import pokedex.composeapp.generated.resources.language_game_info_value
import pokedex.composeapp.generated.resources.language_ui
import pokedex.composeapp.generated.resources.language_ui_value
import pokedex.composeapp.generated.resources.link_about
import pokedex.composeapp.generated.resources.link_about_description
import pokedex.composeapp.generated.resources.link_help
import pokedex.composeapp.generated.resources.link_help_description
import pokedex.composeapp.generated.resources.link_terms
import pokedex.composeapp.generated.resources.link_terms_description
import pokedex.composeapp.generated.resources.logged_in_as
import pokedex.composeapp.generated.resources.logout
import pokedex.composeapp.generated.resources.mega_evolutions
import pokedex.composeapp.generated.resources.mega_evolutions_description
import pokedex.composeapp.generated.resources.other_forms
import pokedex.composeapp.generated.resources.other_forms_description
import pokedex.composeapp.generated.resources.pokedex_updates
import pokedex.composeapp.generated.resources.pokedex_updates_description
import pokedex.composeapp.generated.resources.row_version
import pokedex.composeapp.generated.resources.row_version_value
import pokedex.composeapp.generated.resources.section_general
import pokedex.composeapp.generated.resources.section_language
import pokedex.composeapp.generated.resources.section_notifications
import pokedex.composeapp.generated.resources.section_others
import pokedex.composeapp.generated.resources.section_pokedex
import pokedex.composeapp.generated.resources.world_news
import pokedex.composeapp.generated.resources.world_news_description

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    isLoggedIn: Boolean,
    showSuccess: Boolean,
    name: String = "",
    email: String = "",
    onLoginClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onNameClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onPasswordClick: () -> Unit = {},
    onTabSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {

    AppContainer(
        modifier,
        bottomBar = AppBottomNavigation(
            selectedRoute = ScreenRouter.Profile.route,
            onTabSelected = onTabSelected
        )
    ) { modifier ->
        LazyColumn(
            modifier = modifier
        ) {
            if (!isLoggedIn) {
                item { GuestProfileHeader(onClick = onLoginClick) }
            } else {
                item { LoggedProfileHeader(name, email, onNameClick, onEmailClick, onPasswordClick) }
            }
            item { SectionTitle(Res.string.section_pokedex) }
            item { TextRow(
                Res.string.mega_evolutions,
                Res.string.mega_evolutions_description,
                isEnabled = false, onCheckedChange = {}
            )
            }

            item { TextRow(
                Res.string.other_forms,
                Res.string.other_forms_description,
                isEnabled = false, onCheckedChange = {})
            }
            item { SectionTitle(Res.string.section_notifications) }
            item {
                TextRow(
                    Res.string.pokedex_updates,
                    Res.string.pokedex_updates_description,
                    isEnabled = true, onCheckedChange = {})
            }
            item { TextRow(
                Res.string.world_news,
                Res.string.world_news_description,
                isEnabled = true, onCheckedChange = {})
            }

            item { SectionTitle(Res.string.section_language) }
            item { TextRow(Res.string.language_ui, Res.string.language_ui_value) }
            item { TextRow(Res.string.language_game_info, Res.string.language_game_info_value) }

            item { SectionTitle(Res.string.section_general) }
            item { TextRow(Res.string.row_version, Res.string.row_version_value) }
            item { TextRow(Res.string.link_terms, Res.string.link_terms_description) }
            item { TextRow(Res.string.link_help, Res.string.link_help_description) }
            item { TextRow(Res.string.link_about, Res.string.link_about_description) }

            if (isLoggedIn) {
                item { SectionTitle(Res.string.section_others) }
                item {
                    TextRow(
                        Res.string.logout,
                        stringResource(Res.string.logged_in_as, name),
                        onClick = onLogoutClick,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    PokedexTheme {
        ProfileScreen(
            isLoggedIn = true,
            showSuccess = true,
            name = "Diego",
            email = "diego@gmail.com"
        )
    }
}
