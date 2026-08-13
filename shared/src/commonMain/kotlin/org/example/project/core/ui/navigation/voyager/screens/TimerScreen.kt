package org.example.project.core.ui.navigation.voyager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.example.project.core.domain.model.Recipe
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.features.timer.ui.composables.TimerScreen
import org.example.project.features.timer.ui.vm.TimerScreenModel
import org.koin.core.parameter.parametersOf

class TimerScreen(private val recipe: Recipe) : Screen {
    @Composable
    override fun Content() {
        Scaffold { paddingValues ->
            TimerScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(
                        start = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        end = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
                recipe = recipe,
                screenModel = koinScreenModel<TimerScreenModel>(
                    parameters = { parametersOf(recipe) }
                )
            )
        }

    }
}