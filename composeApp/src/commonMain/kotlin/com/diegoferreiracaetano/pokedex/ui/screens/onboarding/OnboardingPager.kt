// File: ui/screens/OnboardingPager.kt
package com.diegoferreiracaetano.pokedex.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.pager.PagedContent
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


data class OnboardingPageData(
    val imageRes: DrawableResource,
    val imageContentDescription: String,
    val title: String,
    val description: String,
    val buttonText: String
)

@Composable
fun OnboardingPager(
    pages: List<OnboardingPageData>,
    modifier: Modifier = Modifier,
    onOnboardingFinished: () -> Unit,
) {
    if (pages.isEmpty()) return

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagedContent(
            items = pages,
            showIndicator = true,
            pageContent = { pageData, pageIndex ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(0.5f))

                    Image(
                        painter = painterResource(pageData.imageRes),
                        contentDescription = pageData.imageContentDescription,
                        modifier = Modifier
                            .fillMaxWidth(if (pageIndex == 0) 0.8f else 0.6f)
                            .aspectRatio(if (pageIndex == 0) 1.5f else 1f),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    Text(
                        text = pageData.title,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = pageData.description,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }
            },
            bottomContent = { currentPage, isLastPage, onNext ->
                AppButton(
                    text = if (isLastPage) {
                        pages.last().buttonText
                    } else {
                        pages[currentPage].buttonText
                    },
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

@Preview
@Composable
private fun OnboardingPagerPreview() {
    PokedexTheme {
        OnboardingPager(
            pages = onboardingPages,
            onOnboardingFinished = { }
        )
    }
}
