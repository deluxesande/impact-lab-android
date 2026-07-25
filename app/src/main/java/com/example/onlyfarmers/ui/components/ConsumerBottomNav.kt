package com.example.onlyfarmers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted

enum class ConsumerTab { Home, Search, Basket, Orders, Account }

@Composable
fun ConsumerBottomNav(
    activeTab: ConsumerTab,
    navController: NavController,
    basketCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        NavTab(
            icon = Icons.Rounded.Home,
            label = "Home",
            active = activeTab == ConsumerTab.Home,
            onClick = {
                if (activeTab != ConsumerTab.Home)
                    navController.navigate(Screen.ConsumerHome.route) {
                        popUpTo(Screen.ConsumerHome.route) { inclusive = true }
                    }
            },
        )
        NavTab(
            icon = Icons.Rounded.Search,
            label = "Search",
            active = activeTab == ConsumerTab.Search,
            onClick = {},
        )
        Box {
            NavTab(
                icon = Icons.Rounded.ShoppingBag,
                label = "Basket",
                active = activeTab == ConsumerTab.Basket,
                onClick = {
                    if (activeTab != ConsumerTab.Basket)
                        navController.navigate(Screen.Cart.route)
                },
            )
            if (basketCount > 0) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(SavingsOrange)
                        .align(Alignment.TopEnd)
                        .offset(x = (-10).dp, y = 2.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = basketCount.toString(),
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        NavTab(
            icon = Icons.Rounded.ReceiptLong,
            label = "Orders",
            active = activeTab == ConsumerTab.Orders,
            onClick = {},
        )
        NavTab(
            icon = Icons.Rounded.AccountCircle,
            label = "Account",
            active = activeTab == ConsumerTab.Account,
            onClick = {},
        )
    }
}

@Composable
private fun NavTab(
    icon: ImageVector,
    label: String,
    active: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .height(52.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (active) BoardInk else TextMuted,
            modifier = Modifier.size(22.dp),
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
            color = if (active) BoardInk else TextMuted,
        )
    }
}
