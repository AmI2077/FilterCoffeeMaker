package org.example.project.core.ui.navigation.voyager

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import org.example.project.core.ui.theme.black
import org.example.project.core.ui.theme.white

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    currentTab: Tab,
    onItemClick: (Tab) -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp,
        windowInsets = WindowInsets(0, 10, 0, 10)
    ) {
        TOP_LEVEL_ROUTES.forEach { tab ->
            val isSelected = currentTab == tab
            val iconSize = if (isSelected) 32.dp else 30.dp

            NavigationBarItem(
                selected = isSelected,
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        painter = tab.options.icon!!,
                        contentDescription = null
                    )
                },
                onClick = {
                    onItemClick(tab)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = white,
                    indicatorColor = black,
                    unselectedIconColor = black
                )
            )
        }
    }
}

