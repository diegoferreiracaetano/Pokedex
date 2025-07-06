// CollapsingScreenLayout.kt

package com.diegoferreiracaetano.pokedex.ui.components.pokedex.test

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private class CollapsingLayoutData(val type: CollapsingLayoutContentType) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@CollapsingLayoutData
}

private enum class CollapsingLayoutContentType {
    Collapsing, Fixed
}

fun Modifier.collapsingContent() = this.then(CollapsingLayoutData(CollapsingLayoutContentType.Collapsing))
fun Modifier.fixedContent() = this.then(CollapsingLayoutData(CollapsingLayoutContentType.Fixed))

@Composable
fun CollapsingHeaderLayout(
    collapsedHeight: Dp,
    expandedHeight: Dp,
    scrollOffset: Float,
    modifier: Modifier = Modifier,
    collapsingContent: @Composable () -> Unit,
    fixedContent: @Composable () -> Unit
) {
    val expandedHeightPx = with(LocalDensity.current) { expandedHeight.toPx() }

    Layout(
        content = {
            Box(modifier = Modifier.collapsingContent()) { collapsingContent() }
            Box(modifier = Modifier.fixedContent()) { fixedContent() }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(expandedHeight)
    ) { measurables, constraints ->
        val collapsingPlaceable = measurables.first {
            (it.parentData as? CollapsingLayoutData)?.type == CollapsingLayoutContentType.Collapsing
        }.measure(constraints)

        val fixedPlaceable = measurables.first {
            (it.parentData as? CollapsingLayoutData)?.type == CollapsingLayoutContentType.Fixed
        }.measure(constraints)

        layout(constraints.maxWidth, expandedHeightPx.roundToInt()) {
            collapsingPlaceable.placeRelative(0, scrollOffset.roundToInt())
            fixedPlaceable.placeRelative(0, 0)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingScreenLayout(
    expandedHeaderHeight: Dp = 250.dp,
    collapsedHeaderHeight: Dp = 56.dp,
    toolbarTitle: String,
    toolbarBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    expandedHeaderContent: @Composable (collapsedFraction: Float) -> Unit,
    screenContent: @Composable (paddingValues: PaddingValues) -> Unit
) {
    val density = LocalDensity.current
    val expandedHeightPx = with(density) { expandedHeaderHeight.toPx() }
    val collapsedHeightPx = with(density) { collapsedHeaderHeight.toPx() }

    val topBarOffset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = (topBarOffset.value + delta).coerceIn(
                    -(expandedHeightPx - collapsedHeightPx), 0f
                )
                scope.launch { topBarOffset.snapTo(newOffset) }
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                val threshold = -(expandedHeightPx - collapsedHeightPx) / 2
                val targetOffset = if (topBarOffset.value < threshold) {
                    -(expandedHeightPx - collapsedHeightPx)
                } else {
                    0f
                }
                scope.launch {
                    topBarOffset.animateTo(targetOffset, tween(300))
                }
                return Velocity.Zero
            }
        }
    }

    val collapsedFraction = (topBarOffset.value / -(expandedHeightPx - collapsedHeightPx)).coerceIn(0f, 1f)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            screenContent(
                PaddingValues(
                    top = expandedHeaderHeight,
                    bottom = paddingValues.calculateBottomPadding(),
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
                )
            )

            CollapsingHeaderLayout(
                collapsedHeight = collapsedHeaderHeight,
                expandedHeight = expandedHeaderHeight,
                scrollOffset = topBarOffset.value,
                collapsingContent = {
                    expandedHeaderContent(collapsedFraction)
                },
                fixedContent = {
                    TopAppBar(
                        title = {
                            if (collapsedFraction > 0.95f) {
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxHeight()
                                ) {
                                    Text(
                                        text = toolbarTitle,
                                        color = Color.White,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = "Subtitle here",
                                        color = Color.White.copy(alpha = 0.7f),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        },
                        navigationIcon = navigationIcon,
                        actions = actions,
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = toolbarBackgroundColor
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(collapsedHeaderHeight)
                            .background(toolbarBackgroundColor.copy(alpha = collapsedFraction))
                    )

                }
            )
        }
    }
}
