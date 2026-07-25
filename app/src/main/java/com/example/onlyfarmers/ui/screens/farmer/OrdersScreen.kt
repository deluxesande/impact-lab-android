package com.example.onlyfarmers.ui.screens.farmer

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.FarmerBottomNav
import com.example.onlyfarmers.ui.components.FarmerTab
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private data class CompletedOrder(
    val buyer: String,
    val item: String,
    val payout: Int,
)

private val completedOrders = listOf(
    CompletedOrder("Brian K.", "8 kg sukuma", 200),
    CompletedOrder("Njoki M.", "20 kg nyanya", 900),
)

// S9 — Orders · one decision at a time
@Composable
fun OrdersScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
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
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = if (lang == "EN") "Orders" else "Oda",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                )
                Text(
                    text = if (lang == "EN") "1 of 2" else "1 ya 2",
                    style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                    color = TextOnDarkMuted,
                )
            }

            // Stacked card (drawBehind to simulate the CSS box-shadow stack)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .drawBehind {
                        val r = CornerRadius(26.dp.toPx())
                        // Bottom stack: 30dp down, 12dp narrower each side
                        drawRoundRect(
                            color = Cream.copy(alpha = 0.10f),
                            topLeft = Offset(12.dp.toPx(), 30.dp.toPx()),
                            size = Size(size.width - 24.dp.toPx(), size.height),
                            cornerRadius = r,
                        )
                        // Middle stack: 18dp down, 6dp narrower each side
                        drawRoundRect(
                            color = Cream.copy(alpha = 0.22f),
                            topLeft = Offset(6.dp.toPx(), 18.dp.toPx()),
                            size = Size(size.width - 12.dp.toPx(), size.height),
                            cornerRadius = r,
                        )
                    },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(26.dp))
                        .background(Cream)
                        .padding(22.dp),
                ) {
                    // Buyer row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFDCD9CC)),
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Amina W.",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = BoardInk,
                            )
                            Text(
                                text = "Kilimani · 6.2 km",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                color = Color(0xFF6D6A5E),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(999.dp))
                                .background(BoardInk)
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                        ) {
                            Text(
                                text = if (lang == "EN") "NEW" else "MPYA",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                ),
                                color = Cream,
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    // Produce
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .background(Color(0xFFE0DED3)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "🍅",
                                fontSize = 28.sp,
                            )
                        }
                        Column {
                            Text(
                                text = "12 kg",
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    lineHeight = 1.em,
                                ),
                                color = BoardInk,
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = if (lang == "EN") "Tomatoes · Grade A" else "Nyanya · Grade A",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                                color = Color(0xFF6D6A5E),
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Dashed divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .drawBehind {
                                val dashLen = 8.dp.toPx()
                                val gapLen = 6.dp.toPx()
                                var x = 0f
                                while (x < size.width) {
                                    drawLine(
                                        color = BoardInk.copy(alpha = 0.18f),
                                        start = Offset(x, 0f),
                                        end = Offset(minOf(x + dashLen, size.width), 0f),
                                        strokeWidth = 1.dp.toPx(),
                                    )
                                    x += dashLen + gapLen
                                }
                            },
                    )

                    Spacer(Modifier.height(16.dp))

                    // Payout + pickup time
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Column {
                            Text(
                                text = if (lang == "EN") "YOU RECEIVE" else "UTAPATA",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 0.08.em,
                                    fontSize = 10.sp,
                                ),
                                color = Color(0xFF6D6A5E),
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = "KES 540",
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
                                color = BoardInk,
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = if (lang == "EN") "Boda 11:30" else "Boda 11:30",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                color = Color(0xFF6D6A5E),
                                lineHeight = 1.5.em,
                            )
                            Text(
                                text = if (lang == "EN") "pickup at farm" else "pickup shambani",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                color = Color(0xFF6D6A5E),
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    // Action buttons
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        // Reject
                        Column(
                            modifier = Modifier
                                .width(76.dp)
                                .height(66.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .border(1.5.dp, BoardInk.copy(alpha = 0.18f), RoundedCornerShape(18.dp))
                                .clickable { },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Icon(Icons.Rounded.Cancel, null, tint = Color(0xFF6D6A5E), modifier = Modifier.size(20.dp))
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = if (lang == "EN") "Reject" else "Kataa",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = Color(0xFF6D6A5E),
                            )
                        }
                        // Accept
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(66.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .background(BoardInk)
                                .clickable { },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = if (lang == "EN") "ACCEPT · POKEA" else "POKEA · ACCEPT",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                                color = FarmLime,
                            )
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    // Voice affordance
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .clip(CircleShape)
                                .background(BoardInk),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(Icons.Rounded.Mic, null, tint = FarmLime, modifier = Modifier.size(15.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = if (lang == "EN") "Listen and respond by voice" else "Nisikilizie kwa sauti",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            color = Color(0xFF6D6A5E),
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Completed this week
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = if (lang == "EN") "COMPLETED THIS WEEK" else "IMEKAMILIKA WIKI HII",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.10.em,
                        fontSize = 10.sp,
                    ),
                    color = TextOnDarkMuted,
                )
                Spacer(Modifier.height(10.dp))
                completedOrders.forEach { order ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .border(
                                width = 0.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(0.dp),
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(Cream.copy(alpha = 0.12f)),
                        )
                        Text(
                            text = "${order.buyer} · ${order.item}",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = Cream,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = "+${order.payout}",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            color = FarmLime,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Cream.copy(alpha = 0.10f)),
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
        }

        FarmerBottomNav(
            activeTab = FarmerTab.Orders,
            navController = navController,
            lang = lang,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
