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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ReceiptLong
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
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

enum class FarmerTab { Home, Orders, Pesa }

@Composable
fun FarmerBottomNav(
    activeTab: FarmerTab,
    navController: NavController,
    lang: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BoardInk)
            .navigationBarsPadding()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        FarmerNavTab(
            icon = Icons.Rounded.Home,
            label = if (lang == "EN") "Home" else "Nyumbani",
            active = activeTab == FarmerTab.Home,
            onClick = {
                if (activeTab != FarmerTab.Home)
                    navController.navigate(Screen.FarmerDashboard.route) {
                        popUpTo(Screen.FarmerDashboard.route) { inclusive = true }
                    }
            },
        )
        FarmerNavTab(
            icon = Icons.Rounded.ReceiptLong,
            label = if (lang == "EN") "Orders" else "Oda",
            active = activeTab == FarmerTab.Orders,
            onClick = {
                if (activeTab != FarmerTab.Orders)
                    navController.navigate(Screen.FarmerOrders.route)
            },
        )
        FarmerNavTab(
            icon = Icons.Rounded.AccountBalanceWallet,
            label = if (lang == "EN") "Earnings" else "Pesa",
            active = activeTab == FarmerTab.Pesa,
            onClick = {
                if (activeTab != FarmerTab.Pesa)
                    navController.navigate(Screen.Earnings.route)
            },
        )
    }
}

@Composable
private fun FarmerNavTab(
    icon: ImageVector,
    label: String,
    active: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (active) Cream.copy(alpha = 0.10f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (active) FarmLime else TextOnDarkMuted,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.SemiBold,
            color = if (active) Cream else TextOnDarkMuted,
        )
    }
}
