package com.diegoferreiracaetano.pokedex.ui.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.image1
import pokedex.composeapp.generated.resources.image2

val onboardingPages = listOf(
    OnboardingPageData(
        imageRes = Res.drawable.image1,
        imageContentDescription = "Pokémon personagem 1",
        title = "Todos os Pokémons em um só Lugar",
        description = "Acesse uma vasta lista de Pokémon de todas as gerações já feitas pela Nintendo",
        buttonText = "Continuar"
    ),
    OnboardingPageData(
        imageRes = Res.drawable.image2,
        imageContentDescription = "Pokémon personagem 2",
        title = "Mantenha sua Pokédex atualizada",
        description = "Cadastre-se e mantenha seu perfil, pokémon favoritos, configurações e muito mais, salvos no aplicativo, mesmo sem conexão com a internet.",
        buttonText = "Vamos começar!"
    )
)

@Composable
fun OnboardingScreen(
    onOnboardingFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    OnboardingPager(
        pages = onboardingPages,
        modifier = modifier,
        onOnboardingFinished = onOnboardingFinished
    )

}

@Preview
@Composable
fun OnboardingScreenPreview() {
    PokedexTheme {
        OnboardingScreen(
            onOnboardingFinished = {}
        )
    }
}
