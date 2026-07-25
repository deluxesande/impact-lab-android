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
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Star
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
import com.example.onlyfarmers.ui.components.FarmerBottomNav
import com.example.onlyfarmers.ui.components.FarmerTab
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private data class ActiveListing(
    val emoji: String,
    val name: String,
    val nameSw: String,
    val price: Int,
    val qty: Int,
    val unit: String,
)

private val activeListings = listOf(
    ActiveListing("🍅", "Tomatoes", "Nyanya", 45, 120, "kg"),
    ActiveListing("🥬", "Kale", "Sukuma wiki", 25, 80, "bunches"),
)

// S15 — Farmer Profile · stats + settings
@Composable
fun FarmerProfileScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
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
                    text = if (lang == "EN") "Account" else "Akaunti",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = true)
            }

            // Avatar + name
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(Cream.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("👨‍🌾", fontSize = 40.sp)
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Joseph Mwangi",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Kiambu · mkulima 3 seasons",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(Icons.Rounded.Star, null, tint = FarmLime, modifier = Modifier.size(14.dp))
                    Text(
                        text = "4.9",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Cream,
                    )
                    Text(
                        text = "· 312 orders fulfilled",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextOnDarkMuted,
                    )
                }
            }

            Spacer(Modifier.height(22.dp))

            // Stats row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Cream.copy(alpha = 0.08f))
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FarmerStat(
                    value = "KES 18.4k",
                    label = if (lang == "EN") "this month" else "mwezi huu",
                )
                Box(modifier = Modifier.width(1.dp).height(40.dp).background(Cream.copy(alpha = 0.15f)))
                FarmerStat(
                    value = "2,840",
                    label = if (lang == "EN") "kg sold" else "kg iliyouzwa",
                )
                Box(modifier = Modifier.width(1.dp).height(40.dp).background(Cream.copy(alpha = 0.15f)))
                FarmerStat(
                    value = "0",
                    label = if (lang == "EN") "broker cuts" else "mkata dalali",
                )
            }

            Spacer(Modifier.height(22.dp))

            // Active listings
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = if (lang == "EN") "ACTIVE LISTINGS" else "ORODHA ZINAZOENDELEA",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.09.em,
                        fontSize = 10.sp,
                    ),
                    color = TextOnDarkMuted,
                )
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Cream.copy(alpha = 0.08f)),
                ) {
                    activeListings.forEachIndexed { idx, listing ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Cream.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(listing.emoji, fontSize = 20.sp)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (lang == "EN") listing.name else listing.nameSw,
                                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Cream,
                                )
                                Text(
                                    text = "${listing.qty} ${listing.unit} · KES ${listing.price}/${listing.unit.dropLast(1).ifEmpty { listing.unit }}",
                                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                    color = TextOnDarkMuted,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(FarmLime.copy(alpha = 0.18f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                            ) {
                                Text(
                                    text = if (lang == "EN") "LIVE" else "INAENDELEA",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp,
                                    ),
                                    color = FarmLime,
                                )
                            }
                        }
                        if (idx < activeListings.lastIndex) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 72.dp)
                                    .height(1.dp)
                                    .background(Cream.copy(alpha = 0.08f)),
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            // Settings
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = if (lang == "EN") "SETTINGS" else "MIPANGILIO",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.09.em,
                        fontSize = 10.sp,
                    ),
                    color = TextOnDarkMuted,
                )
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Cream.copy(alpha = 0.08f)),
                ) {
                    DarkSettingsRow(
                        icon = Icons.Rounded.Notifications,
                        label = if (lang == "EN") "Notifications" else "Arifa",
                        value = if (lang == "EN") "On" else "Imewashwa",
                    )
                    Box(modifier = Modifier.fillMaxWidth().padding(start = 56.dp).height(1.dp).background(Cream.copy(alpha = 0.08f)))
                    DarkSettingsRow(
                        icon = Icons.Rounded.HelpOutline,
                        label = if (lang == "EN") "Help & FAQ" else "Msaada & Maswali",
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Cream.copy(alpha = 0.08f))
                        .clickable {
                            navController.navigate(Screen.Splash.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(Icons.Rounded.Logout, null, tint = Color(0xFFE87040), modifier = Modifier.size(18.dp))
                    Text(
                        text = if (lang == "EN") "Sign out" else "Toka",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color(0xFFE87040),
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = "OnlyFarmers v1.0 · Nairobi, Kenya",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontSize = 10.sp),
                color = TextOnDarkMuted,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp),
            )
        }

        FarmerBottomNav(
            activeTab = FarmerTab.Account,
            navController = navController,
            lang = lang,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun FarmerStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
            ),
            color = Cream,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextOnDarkMuted,
        )
    }
}

@Composable
private fun DarkSettingsRow(icon: ImageVector, label: String, value: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, null, tint = TextOnDarkMuted, modifier = Modifier.size(18.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Cream,
            modifier = Modifier.weight(1f),
        )
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                color = TextOnDarkMuted,
            )
        } else {
            Icon(Icons.Rounded.ChevronRight, null, tint = TextOnDarkMuted, modifier = Modifier.size(18.dp))
        }
    }
}
