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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.TrendingUp
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private data class PeriodData(
    val label: String,
    val bars: List<Float>,
    val xLabels: List<String>,
    val current: Int,
    val delta: Int,
    val brokerRate: Int,
)

private val periods = listOf(
    PeriodData(
        label = "1W", bars = listOf(0.72f, 0.68f, 0.80f, 0.75f, 0.85f, 0.90f, 1.00f),
        xLabels = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"),
        current = 45, delta = 6, brokerRate = 18,
    ),
    PeriodData(
        label = "1M", bars = listOf(0.60f, 0.55f, 0.65f, 0.58f, 0.70f, 0.75f, 0.68f, 0.80f, 0.72f, 0.85f, 0.78f, 0.90f, 0.82f, 0.95f, 0.88f, 1.00f),
        xLabels = listOf("Wk1", "", "", "", "Wk2", "", "", "", "Wk3", "", "", "", "Wk4", "", "", ""),
        current = 45, delta = 8, brokerRate = 18,
    ),
    PeriodData(
        label = "3M", bars = listOf(0.50f, 0.55f, 0.48f, 0.60f, 0.58f, 0.65f, 0.62f, 0.70f, 0.68f, 0.75f, 0.72f, 0.80f, 0.78f, 0.85f, 0.82f, 0.88f, 0.85f, 0.90f, 0.88f, 0.92f, 0.90f, 0.95f, 0.92f, 1.00f),
        xLabels = listOf("May", "", "", "", "", "", "", "", "Jun", "", "", "", "", "", "", "", "Jul", "", "", "", "", "", "", ""),
        current = 45, delta = 12, brokerRate = 18,
    ),
)

@Composable
fun RateHistoryScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var selectedPeriod by remember { mutableIntStateOf(0) }
    val data = periods[selectedPeriod]

    Box(modifier = Modifier.fillMaxSize().background(BoardInk)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 32.dp),
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
                        .background(Cream.copy(alpha = 0.12f))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.ArrowBack, "Back", tint = Cream, modifier = Modifier.size(18.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "TOMATOES · KIAMBU",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.10.em,
                            fontSize = 10.sp,
                        ),
                        color = TextOnDarkMuted,
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = if (lang == "EN") "Rate history" else "Historia ya bei",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = Cream,
                    )
                }
            }

            // Big current rate
            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = "${data.current}",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.03).em,
                        lineHeight = 0.9.em,
                        fontSize = 64.sp,
                    ),
                    color = FarmLime,
                )
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = "KES / kg",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = Cream.copy(alpha = 0.80f),
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(FarmLime.copy(alpha = 0.18f))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        Icon(Icons.Rounded.TrendingUp, null, tint = FarmLime, modifier = Modifier.size(12.dp))
                        Text(
                            text = "+${data.delta}%",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = FarmLime,
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            // Broker rate comparison
            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(Icons.Rounded.TrendingDown, null, tint = Color(0xFFCD5524), modifier = Modifier.size(14.dp))
                Text(
                    text = if (lang == "EN")
                        "Broker buys at KES ${data.brokerRate}/kg — you earn ${data.current - data.brokerRate} more"
                    else
                        "Dalali hunua KES ${data.brokerRate}/kg — unapata ${data.current - data.brokerRate} zaidi",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextOnDarkMuted,
                )
            }

            Spacer(Modifier.height(24.dp))

            // Period switcher
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Cream.copy(alpha = 0.08f))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                periods.forEachIndexed { idx, period ->
                    val active = idx == selectedPeriod
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(9.dp))
                            .background(if (active) FarmLime else Color.Transparent)
                            .clickable { selectedPeriod = idx }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = period.label,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = if (active) FontWeight.ExtraBold else FontWeight.Medium,
                            ),
                            color = if (active) BoardInk else Cream.copy(alpha = 0.55f),
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Bar chart
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .background(Cream.copy(alpha = 0.05f))
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    data.bars.forEach { h ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(h)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(
                                    when {
                                        h == 1.0f -> FarmLime
                                        h > 0.75f -> FarmLime.copy(alpha = 0.55f)
                                        else -> Cream.copy(alpha = 0.20f)
                                    }
                                ),
                        )
                    }
                }
                // X-axis labels
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                        .background(Cream.copy(alpha = 0.03f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    data.xLabels.forEach { lbl ->
                        Text(
                            text = lbl,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 8.sp,
                            ),
                            color = if (lbl.isNotEmpty()) TextOnDarkMuted else Color.Transparent,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Stat row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                RateStat(
                    label = if (lang == "EN") "Period high" else "Juu zaidi",
                    value = "KES 48",
                    modifier = Modifier.weight(1f),
                )
                RateStat(
                    label = if (lang == "EN") "Period low" else "Chini zaidi",
                    value = "KES 38",
                    modifier = Modifier.weight(1f),
                )
                RateStat(
                    label = if (lang == "EN") "Average" else "Wastani",
                    value = "KES 43",
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(Modifier.height(20.dp))

            // AI insight card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Cream.copy(alpha = 0.08f))
                    .border(1.dp, FarmLime.copy(alpha = 0.30f), RoundedCornerShape(18.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(Icons.Rounded.AutoAwesome, null, tint = FarmLime, modifier = Modifier.size(14.dp))
                    Text(
                        text = "AI INSIGHT",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.10.em,
                            fontSize = 9.5.sp,
                        ),
                        color = FarmLime,
                    )
                }
                Text(
                    text = if (lang == "EN")
                        "Tomato rates in Kiambu trend up 8–12% in late July historically. Post-rains supply dip expected — consider listing now to lock in this week's rate."
                    else
                        "Bei ya nyanya Kiambu huongezeka 8–12% mwishoni mwa Julai. Baada ya mvua, usambazaji hupungua — fikiria kuweka orodha sasa ili kupata bei ya wiki hii.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = 1.55.em,
                    ),
                    color = Cream.copy(alpha = 0.85f),
                )
            }
        }
    }
}

@Composable
private fun RateStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Cream.copy(alpha = 0.08f))
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
            ),
            color = Cream,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontSize = 9.sp,
            ),
            color = TextOnDarkMuted,
        )
    }
}
