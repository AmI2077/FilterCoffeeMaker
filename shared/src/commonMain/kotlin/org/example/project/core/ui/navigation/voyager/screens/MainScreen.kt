package org.example.project.core.ui.navigation.voyager.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.example.project.core.ui.navigation.voyager.BottomNavigationBar
import org.example.project.core.ui.navigation.voyager.RecentRecipesTab
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.accentColor
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.white

class MainScreen : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        TabNavigator(RecentRecipesTab) { tabNavigator ->
            Scaffold(
                bottomBar = {
                    val currentTab = tabNavigator.current
                    BottomNavigationBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 50.dp,
                                shape = RoundedCornerShape(
                                    topStart = UiDefaults.IMAGE_CORNERS_RADIUS.dp,
                                    topEnd = UiDefaults.IMAGE_CORNERS_RADIUS.dp
                                ),
                                spotColor = black,
                                ambientColor = black
                            )
                            .clip(RoundedCornerShape(
                                topStart = UiDefaults.IMAGE_CORNERS_RADIUS.dp,
                                topEnd = UiDefaults.IMAGE_CORNERS_RADIUS.dp
                            ))
                            .background(white),
                        currentTab = currentTab
                    ) { tab ->
                        tabNavigator.current = tab
                    }
                },
                content = { paddingValues ->
                    AnimatedContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(
                                top = paddingValues.calculateTopPadding(),
                                start = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                                end = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                            ),
                        targetState = tabNavigator.current,
                        transitionSpec = {
                            fadeIn().togetherWith(fadeOut())
                        }
                    ) { targetTab ->
                        tabNavigator.saveableState(key = "currentTab", tab = targetTab) {
                            targetTab.Content()
                        }
                    }
                }
            )
        }
    }
}