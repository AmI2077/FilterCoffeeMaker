package org.example.project.core.ui.navigation.voyager

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.accentColor
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
        modifier = modifier
            .border(
                width = 1.dp,
                color = accentColor,
                shape = RoundedCornerShape(
                    topStart = UiDefaults.IMAGE_CORNERS_RADIUS.dp,
                    topEnd = UiDefaults.IMAGE_CORNERS_RADIUS.dp
                )
            ),
        containerColor = containerColor,
        tonalElevation = 0.dp,
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
                label = {
                    RegularAppText(
                        text = tab.options.title,
                        fontSize = 14.sp,
                    )
                },
                onClick = {
                    onItemClick(tab)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = white,
                    indicatorColor = accentColor,
                    unselectedIconColor = accentColor,
                    selectedTextColor = white,
                    unselectedTextColor = accentColor
                )
            )
        }
    }
}

