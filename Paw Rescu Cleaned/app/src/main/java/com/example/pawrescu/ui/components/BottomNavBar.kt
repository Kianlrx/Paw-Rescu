package com.example.pawrescu.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pawrescu.R

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(percent = 50),
        color = Color.LightGray,
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Home Icon (Bottom Left)
            BottomNavItem(
                iconRes = R.drawable.home_icon,
                contentDescription = "Home",
                isSelected = currentRoute == "home",
                onClick = onNavigateToHome
            )

            // Search Icon (To the right of home_icon)
            BottomNavItem(
                iconRes = R.drawable.search_icon,
                contentDescription = "Search",
                isSelected = currentRoute == "search",
                onClick = onNavigateToSearch
            )

            // List Icon (To the right of search icon)
            BottomNavItem(
                iconRes = R.drawable.list_icon,
                contentDescription = "List",
                isSelected = currentRoute == "dog_list",
                onClick = onNavigateToList
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    @DrawableRes iconRes: Int,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        Color(0xFFE2E6EA) // Light grey highlight
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp)) // Rounded square shape
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
