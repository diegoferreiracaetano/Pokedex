// File: ui/screens/OnboardingScreen.kt
package com.diegoferreiracaetano.pokedex.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.image.CircularImage
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.pager.PagedContent
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.image1
import pokedex.composeapp.generated.resources.image2
import pokedex.composeapp.generated.resources.onboarding_button_1
import pokedex.composeapp.generated.resources.onboarding_button_2
import pokedex.composeapp.generated.resources.onboarding_desc_1
import pokedex.composeapp.generated.resources.onboarding_desc_2
import pokedex.composeapp.generated.resources.onboarding_title_1
import pokedex.composeapp.generated.resources.onboarding_title_2

private val pages = listOf(
    OnboardingPageData(
        imageRes = Res.drawable.image1,
        imageContentDescription = Res.string.onboarding_title_1,
        title = Res.string.onboarding_title_1,
        description = Res.string.onboarding_desc_1,
        buttonText = Res.string.onboarding_button_1
    ),
    OnboardingPageData(
        imageRes = Res.drawable.image2,
        imageContentDescription = Res.string.onboarding_title_1,
        title = Res.string.onboarding_title_2,
        description = Res.string.onboarding_desc_2,
        buttonText = Res.string.onboarding_button_2
    )
)
data class OnboardingPageData(
    val imageRes: DrawableResource,
    val imageContentDescription: StringResource,
    val title: StringResource,
    val description: StringResource,
    val buttonText: StringResource
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onOnboardingFinished: () -> Unit,
) {
    if (pages.isEmpty()) return

    AppContainer(
        modifier = modifier
    ) { modifier ->
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagedContent(
                items = pages,
                showIndicator = true,
                pageContent = { pageData, pageIndex ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))

                        CircularImage(
                            resource =pageData.imageRes,
                            contentDescription = pageData.imageContentDescription,
                            modifier = Modifier.weight(0.4f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(pageData.title),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(pageData.description),
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                },
                bottomContent = { currentPage, isLastPage, onNext ->
                    AppButton(
                        text = stringResource(if (isLastPage) {
                            pages.last().buttonText
                        } else {
                            pages[currentPage].buttonText
                        }),
                        onClick = {
                            if (isLastPage) {
                                onOnboardingFinished()
                            } else {
                                onNext()
                            }
                        }
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingPagerPreview() {
    PokedexTheme {
        OnboardingScreen(
            onOnboardingFinished = { }
        )
    }
}
