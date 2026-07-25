package com.example.onlyfarmers.ui.screens.consumer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.TextMuted

private data class TrackStep(
    val label: String,
    val labelKi: String,
    val detail: String,
    val detailKi: String,
    val done: Boolean,
)

private val trackSteps = listOf(
    TrackStep("Order placed", "Agizo limewekwa", "09:38 · paid on delivery", "09:38 · lipa unapopokea", true),
    TrackStep("Joseph is packing", "Joseph anafunga", "picked this morning", "imechaguliwa asubuhi", true),
    TrackStep("Rider on the way", "Mpiga baiskeli anakuja", "batched with 2 nearby orders", "pamoja na maagizo 2 karibu", false),
    TrackStep("Delivered", "Imefikishwa", "", "", false),
)

// S6 — Confirmed · order tracking
@Composable
fun TrackingScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BuyerGreen.copy(alpha = 0.08f))
            .statusBarsPadding(),
    ) {
        // Map placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFFE2E6DD), Color(0xFFEDF0E8)),
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (lang == "EN") "[ map · rider route ]" else "[ ramani · njia ya mpiga baiskeli ]",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                color = Color(0xFF8F8C80),
            )
            // Rider dot
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(BuyerGreen)
                    .align(Alignment.TopStart)
                    .padding(start = 96.dp, top = 140.dp),
            )
            // Destination pin
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = BoardInk,
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.TopEnd)
                    .padding(end = 66.dp, top = 62.dp),
            )
        }

        // Bottom sheet
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(BuyerGreen.copy(alpha = 0f))  // transparent — parent has bg
                .background(Color(0xFFFBFAF6))
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 22.dp),
        ) {
            // Handle
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(BoardInk.copy(alpha = 0.16f))
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(Modifier.height(18.dp))

            // ETA + order ref
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column {
                    Text(
                        text = if (lang == "EN") "ARRIVING" else "INAFIKA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 0.08.em,
                            fontSize = 10.sp,
                        ),
                        color = TextMuted,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = if (lang == "EN") "24 min" else "dakika 24",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 1.em,
                        ),
                        color = BoardInk,
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "#KE-4821",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextMuted,
                        lineHeight = 1.6.em,
                    )
                    Text(
                        text = if (lang == "EN") "3 items · KES 465" else "vitu 3 · KES 465",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextMuted,
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            // Progress bar (4 segments)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                repeat(4) { i ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(5.dp)
                            .clip(RoundedCornerShape(99.dp))
                            .background(if (i < 2) BuyerGreen else BoardInk.copy(alpha = 0.12f)),
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Timeline steps
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                trackSteps.forEach { step ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(13.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(if (step.done) BuyerGreen else Color.Transparent)
                                .border(
                                    width = if (step.done) 0.dp else 2.dp,
                                    color = BoardInk.copy(alpha = 0.2f),
                                    shape = CircleShape,
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (step.done) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(13.dp),
                                )
                            }
                        }
                        Column {
                            Text(
                                text = if (lang == "EN") step.label else step.labelKi,
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = if (step.done) BoardInk else TextMuted,
                            )
                            if (step.detail.isNotEmpty()) {
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    text = if (lang == "EN") step.detail else step.detailKi,
                                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                    color = if (step.done) TextMuted else Color(0xFFB3B0A4),
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            // Delivery address card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(13.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = null,
                    tint = BuyerGreen,
                    modifier = Modifier.size(20.dp).padding(top = 2.dp),
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Riverside Court, Apt 4B",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = BoardInk,
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = if (lang == "EN")
                            "Ring bell twice · leave with mlinzi"
                        else
                            "Piga kengele mara mbili · acha kwa mlinzi",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            lineHeight = 1.5.em,
                        ),
                        color = TextMuted,
                    )
                }
                Text(
                    text = if (lang == "EN") "Edit" else "Badilisha",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = BuyerGreen,
                )
            }

            Spacer(Modifier.height(10.dp))

            // AI swap note
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(BoardInk),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AutoAwesome,
                        contentDescription = null,
                        tint = FarmLime,
                        modifier = Modifier.size(12.dp),
                    )
                }
                Text(
                    text = if (lang == "EN")
                        "Unga swapped to Halima's — KES 25 cheaper"
                    else
                        "Unga umebadilishwa kwa Halima — KES 25 nafuu",
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 1.45.em),
                    color = BoardInk,
                )
            }

            Spacer(Modifier.height(10.dp))

            // Rider card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(LightSurface),
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Peter · boda 4821",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = BoardInk,
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "KDJ 812H · 4.9 ★",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextMuted,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(BoardInk),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = if (lang == "EN") "Call rider" else "Piga simu",
                        tint = FarmLime,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
