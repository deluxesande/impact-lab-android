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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.VerifiedUser
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
import com.example.onlyfarmers.ui.components.ConsumerBottomNav
import com.example.onlyfarmers.ui.components.ConsumerTab
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

// S13 — Account · profile + settings
@Composable
fun AccountScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
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
                    color = BoardInk,
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = false)
            }

            // Profile card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightSurface)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDCD9CC)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.AccountCircle, null, tint = Color(0xFF8A8779), modifier = Modifier.size(52.dp))
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Amina Waweru",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "amina.w@gmail.com",
                    style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
                Spacer(Modifier.height(16.dp))

                // Stat row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    StatChip(value = "4", label = if (lang == "EN") "orders" else "oda")
                    StatChip(value = "KES 565", label = if (lang == "EN") "saved" else "iliyookoa")
                    StatChip(value = "3", label = if (lang == "EN") "farmers" else "wakulima")
                }
            }

            Spacer(Modifier.height(22.dp))

            // Delivery address
            SettingsSection(title = if (lang == "EN") "DELIVERY" else "UWASILISHAJI") {
                SettingsRow(
                    icon = Icons.Rounded.Place,
                    label = if (lang == "EN") "Delivery address" else "Anwani ya uwasilishaji",
                    value = "Kilimani, Nairobi",
                )
            }

            Spacer(Modifier.height(12.dp))

            // Preferences
            SettingsSection(title = if (lang == "EN") "PREFERENCES" else "MAPENDEKEZO") {
                SettingsRow(
                    icon = Icons.Rounded.Notifications,
                    label = if (lang == "EN") "Notifications" else "Arifa",
                    value = if (lang == "EN") "On" else "Imewashwa",
                )
                SettingsDivider()
                SettingsRow(
                    icon = Icons.Rounded.Language,
                    label = if (lang == "EN") "Language" else "Lugha",
                    trailing = {
                        LangPill(selected = lang, onSelect = onLangChange, onDark = false)
                    },
                )
            }

            Spacer(Modifier.height(12.dp))

            // Support
            SettingsSection(title = if (lang == "EN") "SUPPORT" else "MSAADA") {
                SettingsRow(
                    icon = Icons.Rounded.HelpOutline,
                    label = if (lang == "EN") "Help & FAQ" else "Msaada & Maswali",
                )
                SettingsDivider()
                SettingsRow(
                    icon = Icons.Rounded.VerifiedUser,
                    label = if (lang == "EN") "Terms & Privacy" else "Masharti & Faragha",
                )
            }

            Spacer(Modifier.height(12.dp))

            // Sign out
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(LightSurface)
                    .clickable {
                        navController.navigate(Screen.Splash.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(Icons.Rounded.Logout, null, tint = Color(0xFFCD5524), modifier = Modifier.size(20.dp))
                    Text(
                        text = if (lang == "EN") "Sign out" else "Toka",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color(0xFFCD5524),
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "OnlyFarmers v1.0 · Nairobi, Kenya",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                ),
                color = TextMuted,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp),
            )
        }

        ConsumerBottomNav(
            activeTab = ConsumerTab.Account,
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun StatChip(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
            ),
            color = BoardInk,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextMuted,
        )
    }
}

@Composable
private fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.09.em,
                fontSize = 10.sp,
            ),
            color = TextMuted,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(LightSurface),
        ) {
            content()
        }
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    value: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, null, tint = TextSubtle, modifier = Modifier.size(18.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BoardInk,
            modifier = Modifier.weight(1f),
        )
        when {
            trailing != null -> trailing()
            value != null -> Text(
                text = value,
                style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                color = TextMuted,
            )
            else -> Icon(Icons.Rounded.ChevronRight, null, tint = TextMuted, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
private fun SettingsDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 46.dp)
            .height(1.dp)
            .background(Color(0xFFE8E5DC)),
    )
}
