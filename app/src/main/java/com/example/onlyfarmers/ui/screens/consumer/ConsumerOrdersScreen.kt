package com.example.onlyfarmers.ui.screens.consumer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.ConsumerBottomNav
import com.example.onlyfarmers.ui.components.ConsumerTab
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private enum class OrderStatus { Delivered, InTransit, Preparing }

private data class ConsumerOrder(
    val ref: String,
    val date: String,
    val items: String,
    val itemsSw: String,
    val total: Int,
    val savings: Int,
    val status: OrderStatus,
    val farmer: String,
)

private val pastOrders = listOf(
    ConsumerOrder("#3892", "Today, 11:30", "Tomatoes · Kale · Avocado", "Nyanya · Sukuma · Parachichi", 465, 180, OrderStatus.InTransit, "Joseph M."),
    ConsumerOrder("#3841", "Yesterday", "Carrots · Spinach", "Karoti · Mchicha", 230, 90, OrderStatus.Delivered, "Grace N."),
    ConsumerOrder("#3790", "23 Jul", "Bananas · Tomatoes · Avocado", "Ndizi · Nyanya · Parachichi", 610, 240, OrderStatus.Delivered, "Peter K."),
    ConsumerOrder("#3721", "20 Jul", "Kale · Spinach", "Sukuma · Mchicha", 140, 55, OrderStatus.Delivered, "Amina W."),
)

// S12 — Consumer Orders · history + live status
@Composable
fun ConsumerOrdersScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    val totalSavings = pastOrders.sumOf { it.savings }
    val totalOrders = pastOrders.size

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (lang == "EN") "My Orders" else "Oda Zangu",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = false)
            }

            // Savings summary card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(BoardInk)
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = if (lang == "EN") "TOTAL SAVED" else "JUMLA ILIYOOKOA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.09.em,
                            fontSize = 10.sp,
                        ),
                        color = Color(0xFF9AA78A),
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "KES $totalSavings",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        color = Color(0xFFF3F0E4),
                    )
                    Text(
                        text = if (lang == "EN") "vs. mall prices" else "dhidi ya bei ya mall",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = Color(0xFF9AA78A),
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = if (lang == "EN") "ORDERS" else "ODA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.09.em,
                            fontSize = 10.sp,
                        ),
                        color = Color(0xFF9AA78A),
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "$totalOrders",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        color = Color(0xFFF3F0E4),
                    )
                    Text(
                        text = if (lang == "EN") "all time" else "jumla yote",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = Color(0xFF9AA78A),
                    )
                }
            }

            Spacer(Modifier.height(22.dp))

            Text(
                text = if (lang == "EN") "RECENT ORDERS" else "ODA ZA HIVI KARIBUNI",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.09.em,
                    fontSize = 10.sp,
                ),
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 20.dp),
            )

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                pastOrders.forEach { order ->
                    OrderCard(
                        order = order,
                        lang = lang,
                        onClick = { navController.navigate(Screen.OrderDetail.createRoute(order.ref)) },
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
        }

        ConsumerBottomNav(
            activeTab = ConsumerTab.Orders,
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun OrderCard(order: ConsumerOrder, lang: String, onClick: () -> Unit = {}) {
    val (statusIcon, statusColor, statusLabel, statusLabelSw) = when (order.status) {
        OrderStatus.Delivered -> Quad(Icons.Rounded.CheckCircle, BuyerGreen, "Delivered", "Imewasilishwa")
        OrderStatus.InTransit -> Quad(Icons.Rounded.LocalShipping, SavingsOrange, "On the way", "Inakuja")
        OrderStatus.Preparing -> Quad(Icons.Rounded.Schedule, TextSubtle, "Preparing", "Inaandaliwa")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(LightSurface)
            .clickable(onClick = onClick)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(statusIcon, null, tint = statusColor, modifier = Modifier.size(15.dp))
                Text(
                    text = if (lang == "EN") statusLabel else statusLabelSw,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = statusColor,
                )
            }
            Text(
                text = order.ref,
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                color = TextMuted,
            )
        }

        Spacer(Modifier.height(10.dp))

        Text(
            text = if (lang == "EN") order.items else order.itemsSw,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = BoardInk,
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = "${order.date} · ${order.farmer}",
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextMuted,
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Column {
                Text(
                    text = "KES ${order.total}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = BoardInk,
                )
                Text(
                    text = if (lang == "EN") "saved KES ${order.savings}" else "iliokolewa KES ${order.savings}",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = BuyerGreen,
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(BoardInk)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
            ) {
                Text(
                    text = if (lang == "EN") "Reorder" else "Agiza tena",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                )
            }
        }
    }
}

// Destructuring helper — Kotlin doesn't have 4-value data Pair out of box
private data class Quad<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
