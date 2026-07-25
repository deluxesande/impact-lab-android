package com.example.onlyfarmers.ui.screens

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.GpsFixed
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private val recentLocations = listOf(
    "Kilimani, Nairobi" to "Argwings Kodhek Rd",
    "Westlands, Nairobi" to "Westgate Mall area",
    "Karen, Nairobi" to "Karen Road",
    "Lavington, Nairobi" to "James Gichuru Rd",
)

private val suggestions = listOf(
    "Kileleshwa, Nairobi",
    "Parklands, Nairobi",
    "South C, Nairobi",
    "Ngong Road, Nairobi",
)

// LocationPicker — address search + confirm
@Composable
fun LocationPickerScreen(navController: NavController, lang: String) {
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("Kilimani, Nairobi") }

    val displayList = if (query.isEmpty()) recentLocations
    else recentLocations.filter { it.first.contains(query, ignoreCase = true) } +
        suggestions.filter { it.contains(query, ignoreCase = true) }.map { it to "" }

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
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
                    text = if (lang == "EN") "Delivery address" else "Anwani ya uwasilishaji",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
            }

            // Search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(LightSurface)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Icon(Icons.Rounded.Search, null, tint = TextMuted, modifier = Modifier.size(20.dp))
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = BoardInk),
                    cursorBrush = SolidColor(BuyerGreen),
                    singleLine = true,
                    decorationBox = { inner ->
                        if (query.isEmpty()) {
                            Text(
                                text = if (lang == "EN") "Search estate, street, landmark…"
                                       else "Tafuta mtaa, barabara, alama…",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextMuted,
                            )
                        }
                        inner()
                    },
                )
            }

            Spacer(Modifier.height(16.dp))

            // Use current location row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(BuyerGreen.copy(alpha = 0.08f))
                    .clickable { selected = "Current location"; navController.popBackStack() }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(Icons.Rounded.GpsFixed, null, tint = BuyerGreen, modifier = Modifier.size(20.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (lang == "EN") "Use current location" else "Tumia mahali ulipo sasa",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = BuyerGreen,
                    )
                    Text(
                        text = if (lang == "EN") "GPS accurate to ~50m" else "GPS sahihi hadi ~50m",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = BuyerGreen.copy(alpha = 0.7f),
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Map placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFD4E8C2), Color(0xFFBDD9A8), Color(0xFFD1E6D4)),
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(BoardInk),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Rounded.Place, null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = selected,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = BoardInk,
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Recents / results list
            Text(
                text = if (query.isEmpty()) {
                    if (lang == "EN") "RECENT" else "ZILIZOTUMIKA"
                } else {
                    if (lang == "EN") "RESULTS" else "MATOKEO"
                },
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = androidx.compose.ui.unit.TextUnit(10f, androidx.compose.ui.unit.TextUnitType.Sp),
                ),
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
            ) {
                displayList.forEachIndexed { idx, (name, detail) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selected = name; navController.popBackStack() }
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Icon(
                            imageVector = if (detail.isNotEmpty()) Icons.Rounded.History else Icons.Rounded.Place,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(17.dp),
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = BoardInk,
                            )
                            if (detail.isNotEmpty()) {
                                Text(
                                    text = detail,
                                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                    color = TextMuted,
                                )
                            }
                        }
                    }
                    if (idx < displayList.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 45.dp)
                                .height(1.dp)
                                .background(Color(0xFFF0EDE4)),
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))
        }

        // Confirm CTA
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(ConsumerBg)
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(BoardInk)
                .clickable { navController.popBackStack() },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (lang == "EN") "Deliver to $selected" else "Wasilisha kwa $selected",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                color = Color.White,
            )
        }
    }
}
