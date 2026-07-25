package com.example.onlyfarmers.ui.screens.consumer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private val timeSlots = listOf("7–9 AM", "9–11 AM", "11 AM–1 PM", "2–4 PM")
private val paymentOptions = listOf("M-Pesa", "Card", "Cash on delivery")

// S16 — Checkout · address + slot + payment
@Composable
fun CheckoutScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var selectedSlot by remember { mutableIntStateOf(1) }
    var selectedPayment by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 96.dp),
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
                Text(
                    text = if (lang == "EN") "Checkout" else "Malipo",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {

                // Delivery address
                SectionCard(
                    icon = Icons.Rounded.Place,
                    title = if (lang == "EN") "Delivery address" else "Anwani ya uwasilishaji",
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Kilimani, Nairobi",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = BoardInk,
                            )
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = "Apt 4B, Argwings Kodhek Rd",
                                style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                                color = TextMuted,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(LightSurface)
                                .clickable { navController.navigate(Screen.LocationPicker.route) }
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Icon(Icons.Rounded.Edit, null, tint = TextSubtle, modifier = Modifier.size(13.dp))
                                Text(
                                    text = if (lang == "EN") "Change" else "Badilisha",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                    color = TextSubtle,
                                )
                            }
                        }
                    }
                }

                // Delivery slot
                SectionCard(
                    icon = Icons.Rounded.Schedule,
                    title = if (lang == "EN") "Delivery time" else "Wakati wa uwasilishaji",
                ) {
                    Text(
                        text = if (lang == "EN") "Tomorrow, Sat 26 Jul" else "Kesho, Juma 26 Jul",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = TextSubtle,
                    )
                    Spacer(Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        timeSlots.forEachIndexed { idx, slot ->
                            val active = idx == selectedSlot
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (active) BoardInk else LightSurface)
                                    .border(
                                        width = if (active) 0.dp else 1.dp,
                                        color = BoardInk.copy(alpha = 0.10f),
                                        shape = RoundedCornerShape(10.dp),
                                    )
                                    .clickable { selectedSlot = idx }
                                    .padding(vertical = 9.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = slot,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 10.sp,
                                    ),
                                    color = if (active) Color.White else TextSubtle,
                                )
                            }
                        }
                    }
                }

                // Payment
                SectionCard(
                    icon = Icons.Rounded.CreditCard,
                    title = if (lang == "EN") "Payment method" else "Njia ya malipo",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        paymentOptions.forEachIndexed { idx, method ->
                            val active = idx == selectedPayment
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (active) BuyerGreen.copy(alpha = 0.08f) else LightSurface)
                                    .border(
                                        width = if (active) 1.5.dp else 0.dp,
                                        color = if (active) BuyerGreen else Color.Transparent,
                                        shape = RoundedCornerShape(12.dp),
                                    )
                                    .clickable { selectedPayment = idx }
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                val icon = when (idx) {
                                    0 -> Icons.Rounded.Phone
                                    1 -> Icons.Rounded.CreditCard
                                    else -> Icons.Rounded.Schedule
                                }
                                Icon(icon, null, tint = if (active) BuyerGreen else TextMuted, modifier = Modifier.size(18.dp))
                                Text(
                                    text = method,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = if (active) BoardInk else TextSubtle,
                                    modifier = Modifier.weight(1f),
                                )
                                if (active) Icon(Icons.Rounded.CheckCircle, null, tint = BuyerGreen, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                // Order summary
                SectionCard(
                    icon = null,
                    title = if (lang == "EN") "Order summary" else "Muhtasari wa oda",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SummaryRow(if (lang == "EN") "Subtotal" else "Jumla ndogo", "KES 465")
                        SummaryRow(if (lang == "EN") "Delivery" else "Uwasilishaji", "KES 50")
                        SummaryRow(if (lang == "EN") "Saved vs. mall" else "Iliyookoa", "−KES 180", valueColor = BuyerGreen)
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFE8E5DC)))
                        SummaryRow(
                            label = if (lang == "EN") "Total" else "Jumla",
                            value = "KES 515",
                            labelStyle = FontWeight.Bold,
                            valueStyle = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }

        // Place order CTA
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(ConsumerBg)
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            if (selectedPayment == 0) {
                Text(
                    text = if (lang == "EN") "You'll get an M-Pesa prompt on +254 7XX XXX XXX"
                           else "Utapata ombi la M-Pesa kwa +254 7XX XXX XXX",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(BoardInk)
                    .clickable {
                        navController.navigate(Screen.Tracking.route) {
                            popUpTo(Screen.Cart.route) { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = if (lang == "EN") "Place order · KES 515" else "Weka oda · KES 515",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
private fun SectionCard(
    icon: ImageVector?,
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (icon != null) Icon(icon, null, tint = TextMuted, modifier = Modifier.size(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.04.em,
                ),
                color = TextSubtle,
            )
        }
        content()
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    valueColor: Color = BoardInk,
    labelStyle: FontWeight = FontWeight.Normal,
    valueStyle: FontWeight = FontWeight.SemiBold,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = labelStyle),
            color = if (labelStyle == FontWeight.Bold) BoardInk else TextSubtle,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = valueStyle,
            ),
            color = valueColor,
        )
    }
}
