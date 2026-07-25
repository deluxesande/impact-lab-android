package com.example.onlyfarmers.ui.screens.farmer

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
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.TrendingUp
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
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private enum class FarmerNotifType { Order, Rate, Payout, AI }

private data class FarmerNotif(
    val type: FarmerNotifType,
    val title: String,
    val titleSw: String,
    val body: String,
    val bodySw: String,
    val time: String,
    val unread: Boolean,
)

private val farmerNotifications = listOf(
    FarmerNotif(FarmerNotifType.Order, "New order: 12 kg tomatoes", "Oda mpya: Nyanya 12 kg", "Amina W. from Kilimani placed an order. KES 540. Respond within 2 hrs.", "Amina W. kutoka Kilimani ameweka oda. KES 540. Jibu ndani ya masaa 2.", "Just now", true),
    FarmerNotif(FarmerNotifType.Rate, "Rate up: Tomatoes +6%", "Bei imepanda: Nyanya +6%", "Nairobi demand is rising. Today's fixed rate is KES 45/kg — up from KES 42.", "Mahitaji ya Nairobi yanazidi. Bei ya leo ni KES 45/kg — kutoka KES 42.", "15 min ago", true),
    FarmerNotif(FarmerNotifType.Payout, "M-Pesa received: KES 900", "M-Pesa imepokelewa: KES 900", "Payment for order #3841 (Njoki M. · 20 kg sukuma) has been sent to your M-Pesa.", "Malipo ya oda #3841 (Njoki M. · Sukuma 20 kg) yametumwa kwa M-Pesa yako.", "2 hrs ago", true),
    FarmerNotif(FarmerNotifType.AI, "AI tip: Hold kale 2 days", "Ushauri wa AI: Simamisha sukuma siku 2", "Sukuma supply is high in Nairobi today. Holding 2 days could earn you KES 28/bunch vs today's KES 25.", "Usambazaji wa sukuma ni mwingi Nairobi leo. Kusubiri siku 2 kunaweza kukulipa KES 28/muundo badala ya KES 25 ya leo.", "4 hrs ago", false),
    FarmerNotif(FarmerNotifType.Order, "Order confirmed: Brian K.", "Oda imethibitishwa: Brian K.", "Brian K. confirmed receipt of 8 kg sukuma. Rated your produce ⭐ 5.0.", "Brian K. amethibitisha kupokea sukuma 8 kg. Amekupa kiwango cha ⭐ 5.0.", "Yesterday", false),
    FarmerNotif(FarmerNotifType.Rate, "Rate down: Avocado −12%", "Bei imeshuka: Parachichi −12%", "Oversupply from Murang'a this week. Consider switching to tomatoes.", "Usambazaji mwingi kutoka Murang'a wiki hii. Fikiria kubadilika kwa nyanya.", "2 days ago", false),
)

// S19 — Farmer Notifications
@Composable
fun FarmerNotificationsScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    val unreadCount = farmerNotifications.count { it.unread }

    Box(modifier = Modifier.fillMaxSize().background(BoardInk)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 24.dp),
        ) {
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
                        .background(Cream.copy(alpha = 0.12f))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.ArrowBack, "Back", tint = Cream, modifier = Modifier.size(18.dp))
                }
                Text(
                    text = if (lang == "EN") "Notifications" else "Arifa",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                    modifier = Modifier.weight(1f),
                )
                if (unreadCount > 0) {
                    Text(
                        text = if (lang == "EN") "Mark all read" else "Soma zote",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = FarmLime,
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
                    color = TextOnDarkMuted,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                farmerNotifications.forEachIndexed { idx, notif ->
                    if (idx > 0 && !notif.unread && farmerNotifications[idx - 1].unread) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (lang == "EN") "EARLIER" else "MAPEMA",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                            ),
                            color = TextOnDarkMuted,
                            modifier = Modifier.padding(vertical = 4.dp),
                        )
                    }
                    FarmerNotifCard(notif = notif, lang = lang)
                }
            }
        }
    }
}

@Composable
private fun FarmerNotifCard(notif: FarmerNotif, lang: String) {
    val (bgAlpha, iconTint, icon) = when (notif.type) {
        FarmerNotifType.Order -> Triple(0.14f, FarmLime, Icons.Rounded.ReceiptLong)
        FarmerNotifType.Rate -> if (notif.title.contains("up") || notif.titleSw.contains("imepanda"))
            Triple(0.10f, FarmLime, Icons.Rounded.TrendingUp)
        else Triple(0.10f, SavingsOrange, Icons.Rounded.TrendingDown)
        FarmerNotifType.Payout -> Triple(0.10f, FarmLime, Icons.Rounded.AccountBalanceWallet)
        FarmerNotifType.AI -> Triple(0.10f, TextOnDarkMuted, Icons.Rounded.AutoAwesome)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (notif.unread) Cream.copy(alpha = 0.10f) else Cream.copy(alpha = 0.05f))
            .clickable { }
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(iconTint.copy(alpha = bgAlpha)),
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
                    color = Cream,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = notif.time,
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontSize = 10.sp),
                    color = TextOnDarkMuted,
                )
            }
            Spacer(Modifier.height(3.dp))
            Text(
                text = if (lang == "EN") notif.body else notif.bodySw,
                style = MaterialTheme.typography.bodySmall,
                color = TextOnDarkMuted,
                lineHeight = MaterialTheme.typography.bodySmall.fontSize * 1.5f,
            )
        }
        if (notif.unread) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(FarmLime),
            )
        }
    }
}
