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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.LocalOffer
import androidx.compose.material.icons.rounded.LocalShipping
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private enum class NotifType { Delivery, Order, Deal, AI }

private data class Notif(
    val type: NotifType,
    val title: String,
    val titleSw: String,
    val body: String,
    val bodySw: String,
    val time: String,
    val unread: Boolean,
)

private val notifications = listOf(
    Notif(NotifType.Delivery, "Rider on the way", "Boda anakuja", "Brian is 2.4 km from your door. ETA 24 min.", "Brian yuko km 2.4 kutoka nyumbani kwako. Atafika dakika 24.", "Just now", true),
    Notif(NotifType.Order, "Order confirmed", "Oda imethibitishwa", "Joseph confirmed your order #3892. Tomatoes, Kale, Avocado.", "Joseph amethibitisha oda yako #3892. Nyanya, Sukuma, Parachichi.", "10 min ago", true),
    Notif(NotifType.AI, "AI Basket ready", "Kikapu cha AI kiko tayari", "Based on your last 3 orders, we built a fresh basket — saves you KES 220.", "Kulingana na oda zako 3 za mwisho, tumejenga kikapu kipya — unaokolewa KES 220.", "1 hr ago", true),
    Notif(NotifType.Deal, "New rate: Avocado", "Bei mpya: Parachichi", "Avocados from Murang'a just dropped to KES 20/piece — 67% below Naivas.", "Parachichi kutoka Murang'a zimeshuka hadi KES 20/kipande — 67% chini ya Naivas.", "3 hrs ago", false),
    Notif(NotifType.Order, "Order delivered", "Oda imewasilishwa", "Your order #3841 was delivered. How was the quality?", "Oda yako #3841 imewasilishwa. Ubora ulikuwa vipi?", "Yesterday", false),
    Notif(NotifType.AI, "Price alert: Tomatoes", "Tahadhari ya bei: Nyanya", "Tomato supply is high this week. Lock in today's rate before it changes.", "Usambazaji wa nyanya ni mwingi wiki hii. Funga bei ya leo kabla haijabadilika.", "2 days ago", false),
)

// S18 — Notifications
@Composable
fun NotificationsScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    val unreadCount = notifications.count { it.unread }

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 24.dp),
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(LightSurface)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.ArrowBack, "Back", tint = BoardInk, modifier = Modifier.size(18.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (lang == "EN") "Notifications" else "Arifa",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = BoardInk,
                    )
                }
                if (unreadCount > 0) {
                    Text(
                        text = if (lang == "EN") "Mark all read" else "Soma zote",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = BuyerGreen,
                    )
                }
            }

            if (unreadCount > 0) {
                Text(
                    text = if (lang == "EN") "NEW · $unreadCount" else "MPYA · $unreadCount",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                    ),
                    color = TextMuted,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                notifications.forEachIndexed { idx, notif ->
                    // Section divider between unread and read
                    if (idx > 0 && !notif.unread && notifications[idx - 1].unread) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (lang == "EN") "EARLIER" else "MAPEMA",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                            ),
                            color = TextMuted,
                            modifier = Modifier.padding(vertical = 4.dp),
                        )
                    }
                    NotifCard(notif = notif, lang = lang)
                }
            }
        }
    }
}

@Composable
private fun NotifCard(notif: Notif, lang: String) {
    val (iconBg, iconTint, icon) = when (notif.type) {
        NotifType.Delivery -> Triple(SavingsOrange.copy(alpha = 0.12f), SavingsOrange, Icons.Rounded.LocalShipping)
        NotifType.Order -> Triple(BuyerGreen.copy(alpha = 0.12f), BuyerGreen, Icons.Rounded.CheckCircle)
        NotifType.Deal -> Triple(FarmLime.copy(alpha = 0.18f), Color(0xFF5A8A1A), Icons.Rounded.LocalOffer)
        NotifType.AI -> Triple(BoardInk.copy(alpha = 0.08f), BoardInk, Icons.Rounded.AutoAwesome)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (notif.unread) Color.White else LightSurface)
            .clickable { }
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, null, tint = iconTint, modifier = Modifier.size(20.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (lang == "EN") notif.title else notif.titleSw,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = if (notif.unread) FontWeight.Bold else FontWeight.SemiBold,
                    ),
                    color = BoardInk,
                )
                Text(
                    text = notif.time,
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontSize = 10.sp),
                    color = TextMuted,
                )
            }
            Spacer(Modifier.height(3.dp))
            Text(
                text = if (lang == "EN") notif.body else notif.bodySw,
                style = MaterialTheme.typography.bodySmall,
                color = TextSubtle,
                lineHeight = MaterialTheme.typography.bodySmall.fontSize * 1.5f,
            )
        }
        if (notif.unread) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(BuyerGreen),
            )
        }
    }
}
