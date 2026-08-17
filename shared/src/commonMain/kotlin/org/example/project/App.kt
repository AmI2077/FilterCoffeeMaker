package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.project.core.ui.navigation.voyager.screens.MainScreen
import org.example.project.core.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(MainScreen()) { navigator ->
            SlideTransition(
                navigator = navigator
            )
        }
    }
}