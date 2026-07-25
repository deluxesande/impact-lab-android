package com.example.onlyfarmers.ui.screens.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.FarmerBottomNav
import com.example.onlyfarmers.ui.components.FarmerTab
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private data class WeekBar(val label: String, val fraction: Float, val style: BarType)
private enum class BarType { Solid, Active, Forecast }

private val weekBars = listOf(
    WeekBar("W1", 0.39f, BarType.Solid),
    WeekBar("W2", 0.58f, BarType.Solid),
    WeekBar("W3", 0.49f, BarType.Solid),
    WeekBar("W4", 0.81f, BarType.Active),
    WeekBar("W5", 0.60f, BarType.Forecast),
)

// S10 — Pesa · earnings + AI forecast
@Composable
fun EarningsScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (lang == "EN") "Earnings" else "Pesa",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = true)
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                // Month label + total
                Text(
                    text = if (lang == "EN") "THIS MONTH · JULY" else "MWEZI HUU · JULAI",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.10.em,
                        fontSize = 10.sp,
                    ),
                    color = TextOnDarkMuted,
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text(
                        text = "18,400",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.03).em,
                            lineHeight = 0.95.em,
                            fontSize = 46.sp,
                        ),
                        color = Cream,
                    )
                    Text(
                        text = "KES",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = TextOnDarkMuted,
                        modifier = Modifier.padding(bottom = 6.dp),
                    )
                }
                Spacer(Modifier.height(12.dp))

                // Stat pills
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(FarmLime)
                            .padding(horizontal = 11.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(Icons.Rounded.TrendingUp, null, tint = BoardInk, modifier = Modifier.size(13.dp))
                        Text(
                            text = "31% vs June",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = BoardInk,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(Cream.copy(alpha = 0.10f))
                            .padding(horizontal = 11.dp, vertical = 5.dp),
                    ) {
                        Text(
                            text = if (lang == "EN") "0 broker cuts" else "0 mkata wa dalali",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            color = TextOnDarkMuted,
                        )
                    }
                }

                Spacer(Modifier.height(22.dp))

                // Weekly bar chart
                Row(
                    modifier = Modifier.fillMaxWidth().height(96.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                ) {
                    weekBars.forEach { bar ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(bar.fraction)
                                    .clip(RoundedCornerShape(5.dp))
                                    .then(
                                        when (bar.style) {
                                            BarType.Active -> Modifier.background(FarmLime)
                                            BarType.Solid -> Modifier.background(Cream.copy(alpha = 0.18f))
                                            BarType.Forecast -> Modifier
                                                .background(Cream.copy(alpha = 0.05f))
                                                .drawBehind {
                                                    drawRoundRect(
                                                        color = Cream.copy(alpha = 0.30f),
                                                        cornerRadius = CornerRadius(5.dp.toPx()),
                                                        style = Stroke(
                                                            width = 1.dp.toPx(),
                                                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(3f, 3f), 0f),
                                                        ),
                                                    )
                                                }
                                        }
                                    ),
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = bar.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 9.sp,
                                    fontWeight = if (bar.style == BarType.Active) FontWeight.SemiBold else FontWeight.Normal,
                                ),
                                color = if (bar.style == BarType.Active) Cream else Color(0xFF7E8A70),
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                // AI Forecast heading
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                ) {
                    Icon(Icons.Rounded.AutoAwesome, null, tint = TextOnDarkMuted, modifier = Modifier.size(13.dp))
                    Text(
                        text = "AI FORECAST",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.10.em,
                            fontSize = 10.sp,
                        ),
                        color = TextOnDarkMuted,
                    )
                }

                Spacer(Modifier.height(12.dp))

                // Forecast card 1 — oversupply warning (FarmLime bg)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(FarmLime)
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = if (lang == "EN") "Tomatoes · Kisumu" else "Nyanya · Kisumu",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
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
                            Icon(Icons.Rounded.TrendingDown, null, tint = BoardInk, modifier = Modifier.size(13.dp))
                            Text(
                                text = "12%",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                                color = BoardInk,
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (lang == "EN")
                            "Oversupply. Hold 3 days — price will rise to KES 52."
                        else
                            "Oversupply. Shikilia siku 3 — bei itapanda hadi KES 52.",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 1.5.em),
                        color = BoardInk,
                    )
                }

                Spacer(Modifier.height(11.dp))

                // Forecast card 2 — demand signal (dark bordered)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, Cream.copy(alpha = 0.16f), RoundedCornerShape(20.dp))
                        .background(Cream.copy(alpha = 0.07f))
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = if (lang == "EN") "Kale · Nairobi" else "Sukuma · Nairobi",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = Cream,
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(999.dp))
                                .background(Cream.copy(alpha = 0.14f))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                        ) {
                            Icon(Icons.Rounded.TrendingUp, null, tint = FarmLime, modifier = Modifier.size(13.dp))
                            Text(
                                text = "22%",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                                color = FarmLime,
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (lang == "EN")
                            "Demand is high. Deliver tomorrow morning for the best rate."
                        else
                            "Demand juu. Peleka kesho asubuhi ukipata bei bora.",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 1.5.em),
                        color = Cream.copy(alpha = 0.84f),
                    )
                }

                Spacer(Modifier.height(11.dp))

                // M-Pesa withdraw row (Cream bg)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Cream)
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BoardInk),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Rounded.AccountBalanceWallet, null, tint = FarmLime, modifier = Modifier.size(20.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (lang == "EN") "Withdraw · M-Pesa" else "Toa pesa · M-Pesa",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = BoardInk,
                        )
                        Text(
                            text = "KES 18,400 available now",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = Color(0xFF6D6A5E),
                        )
                    }
                    Icon(Icons.Rounded.ArrowForward, null, tint = BoardInk, modifier = Modifier.size(20.dp))
                }

                Spacer(Modifier.height(8.dp))
            }
        }

        FarmerBottomNav(
            activeTab = FarmerTab.Pesa,
            navController = navController,
            lang = lang,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
