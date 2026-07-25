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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Scale
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

// S8 — New Listing · camera → AI rate
@Composable
fun NewListingScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var kgQty by remember { mutableIntStateOf(48) }
    val totalEarnings = kgQty * 45

    Column(modifier = Modifier.fillMaxSize().background(BoardInk)) {
        // Camera viewfinder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.linearGradient(colors = listOf(Color(0xFF3A3F2F), Color(0xFF454B38)))
                ),
        ) {
            // Status + close
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 22.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(BoardInk.copy(alpha = 0.6f))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.Close, "Close", tint = Cream, modifier = Modifier.size(18.dp))
                }
                Text(
                    text = if (lang == "EN") "New listing" else "Orodha mpya",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Cream.copy(alpha = 0.8f),
                )
                Spacer(Modifier.size(36.dp))
            }

            // Scan frame
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 44.dp)
                    .height(140.dp)
                    .border(2.dp, FarmLime, RoundedCornerShape(14.dp)),
            )

            // AI scan result bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 44.dp, vertical = 22.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BoardInk.copy(alpha = 0.86f))
                    .padding(horizontal = 13.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(FarmLime),
                )
                Text(
                    text = "Tomatoes · Grade A · ~${kgQty} kg",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Cream,
                )
            }
        }

        // Bottom panel
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            Text(
                text = if (lang == "EN") "AI RECOMMENDED · FIXED RATE" else "AI IMEPENDEKEZA · BEI YA KAWAIDA",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.10.em,
                    fontSize = 10.sp,
                ),
                color = TextOnDarkMuted,
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "45",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.035).em,
                            lineHeight = 0.88.em,
                            fontSize = 62.sp,
                        ),
                        color = FarmLime,
                    )
                    Text(
                        text = "KES/kg",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = TextOnDarkMuted,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = if (lang == "EN") "broker offered" else "mteja alitoa",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                    Text(
                        text = "KES 18/kg",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            textDecoration = TextDecoration.LineThrough,
                        ),
                        color = Cream.copy(alpha = 0.5f),
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // AI insight card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .border(1.dp, Cream.copy(alpha = 0.16f), RoundedCornerShape(18.dp))
                    .background(Cream.copy(alpha = 0.07f))
                    .padding(15.dp),
            ) {
                Text(
                    text = if (lang == "EN")
                        "Nairobi demand is high this week. This rate is fixed — no weight reduction."
                    else
                        "Nairobi demand ni juu wiki hii. Bei hii ni fixed — hakuna kupunguza uzito.",
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 1.5.em),
                    color = Cream.copy(alpha = 0.86f),
                )
                Spacer(Modifier.height(13.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Icon(Icons.Rounded.Scale, null, tint = TextOnDarkMuted, modifier = Modifier.size(14.dp))
                        Text(
                            text = if (lang == "EN") "verified weight" else "uzito uliothibitishwa",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextOnDarkMuted,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        Icon(Icons.Rounded.Check, null, tint = TextOnDarkMuted, modifier = Modifier.size(14.dp))
                        Text(
                            text = if (lang == "EN") "guaranteed buyer" else "mnunuzi aliyehakikishwa",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextOnDarkMuted,
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Quantity adjuster
            Text(
                text = if (lang == "EN") "HOW MANY KG?" else "KILO NGAPI?",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.10.em,
                    fontSize = 10.sp,
                ),
                color = TextOnDarkMuted,
            )
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                // Minus button
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, Cream.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                        .background(Cream.copy(alpha = 0.10f))
                        .clickable { if (kgQty > 1) kgQty-- },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.Remove, null, tint = Cream, modifier = Modifier.size(30.dp))
                }
                // Quantity display
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "$kgQty",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 1.em,
                        ),
                        color = Cream,
                    )
                    Text(
                        text = if (lang == "EN") "kg · AI measured" else "kg · AI imepima",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
                // Plus button
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Cream)
                        .clickable { kgQty++ },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.Add, null, tint = BoardInk, modifier = Modifier.size(30.dp))
                }
            }

            Spacer(Modifier.height(16.dp))

            // Total + publish
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (lang == "EN") "You will receive" else "Utapata jumla",
                    style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                    color = TextOnDarkMuted,
                )
                Text(
                    text = "KES ${"%,d".format(totalEarnings)}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                    ),
                    color = FarmLime,
                )
            }

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(FarmLime)
                    .clickable {
                        navController.navigate(Screen.FarmerDashboard.route) {
                            popUpTo(Screen.FarmerDashboard.route) { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = if (lang == "EN") "PUBLISH · CHAPISHA" else "CHAPISHA · PUBLISH",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.02.em,
                    ),
                    color = BoardInk,
                )
            }
        }
    }
}
