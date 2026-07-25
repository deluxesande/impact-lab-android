package com.example.onlyfarmers.ui.screens.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.FarmerBottomNav
import com.example.onlyfarmers.ui.components.FarmerTab
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private val sparkHeights = listOf(0.42f, 0.56f, 0.38f, 0.64f, 0.72f, 0.58f, 1.00f)

// S7 — Farmer Dashboard · today's rate
@Composable
fun DashboardScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(BoardInk)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 80.dp),
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Cream.copy(alpha = 0.14f))
                        .clickable { navController.navigate(Screen.FarmerProfile.route) },
                )
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (lang == "EN") "Hello, Joseph" else "Habari, Joseph",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = Cream,
                    )
                    Text(
                        text = "Kiambu · mkulima 3 seasons",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Cream.copy(alpha = 0.12f))
                        .clickable { navController.navigate(Screen.FarmerNotifications.route) },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.Notifications, null, tint = Cream, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(4.dp))
                LangPill(selected = lang, onSelect = onLangChange, onDark = true)
            }

            // Today's rate card (FarmLime)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(FarmLime)
                    .clickable { navController.navigate(Screen.RateHistory.route) }
                    .padding(horizontal = 20.dp, vertical = 18.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (lang == "EN") "TODAY'S RATE · TOMATOES" else "BEI YA LEO · NYANYA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.10.em,
                            fontSize = 10.sp,
                        ),
                        color = BoardInk,
                    )
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(BoardInk.copy(alpha = 0.14f))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        Icon(Icons.Rounded.TrendingUp, null, tint = BoardInk, modifier = Modifier.size(13.dp))
                        Text(
                            text = "6%",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = BoardInk,
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "45",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.03).em,
                            lineHeight = 0.9.em,
                            fontSize = 52.sp,
                        ),
                        color = BoardInk,
                    )
                    Text(
                        text = "KES / kg",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = BoardInk.copy(alpha = 0.75f),
                        modifier = Modifier.padding(bottom = 6.dp),
                    )
                }
                Spacer(Modifier.height(14.dp))
                // Sparkline
                Row(
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    sparkHeights.forEach { h ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(h)
                                .clip(RoundedCornerShape(3.dp))
                                .background(if (h == 1.0f) BoardInk else BoardInk.copy(alpha = 0.24f)),
                        )
                    }
                }
                Spacer(Modifier.height(9.dp))
                Text(
                    text = if (lang == "EN") "Fixed rate — guaranteed, no bargaining"
                           else "Bei ya kawaida — hakuna kubargain",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = BoardInk.copy(alpha = 0.70f),
                )
            }

            Spacer(Modifier.height(12.dp))

            // Camera CTA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .border(1.dp, Cream.copy(alpha = 0.16f), RoundedCornerShape(22.dp))
                    .background(Cream.copy(alpha = 0.07f))
                    .clickable { navController.navigate(Screen.NewListing.route) }
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(78.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Cream),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.CameraAlt, null, tint = BoardInk, modifier = Modifier.size(40.dp))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (lang == "EN") "Photograph produce" else "Piga picha mazao",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = Cream,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = if (lang == "EN") "Photograph produce → get your rate"
                               else "Piga picha → pata bei yako",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Voice row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .border(1.dp, Cream.copy(alpha = 0.16f), RoundedCornerShape(22.dp))
                    .background(Cream.copy(alpha = 0.07f))
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.size(52.dp).clip(CircleShape).background(FarmLime),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.Mic, null, tint = BoardInk, modifier = Modifier.size(26.dp))
                }
                Spacer(Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (lang == "EN") "Press and speak" else "Bonyeza useme",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Cream,
                    )
                    Text(
                        text = if (lang == "EN") "\"What's the rate for tomatoes today?\""
                               else "\"Nyanya yangu ni bei gani leo?\"",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Orders waiting banner (Cream bg)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Cream)
                    .clickable { navController.navigate(Screen.FarmerOrders.route) }
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Box(
                    modifier = Modifier.size(38.dp).clip(RoundedCornerShape(12.dp)).background(SavingsOrange),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "2",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                        color = Color.White,
                    )
                }
                Column(
                    modifier = Modifier.weight(1f).padding(vertical = 18.dp),
                ) {
                    Text(
                        text = if (lang == "EN") "2 orders waiting" else "Oda mbili zinakusubiri",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = BoardInk,
                    )
                    Text(
                        text = "2 orders waiting · KES 1,080",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = Color(0xFF6D6A5E),
                    )
                }
                Icon(Icons.Rounded.ArrowForward, null, tint = BoardInk, modifier = Modifier.size(20.dp))
            }

            Spacer(Modifier.height(8.dp))
        }

        FarmerBottomNav(
            activeTab = FarmerTab.Home,
            navController = navController,
            lang = lang,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
