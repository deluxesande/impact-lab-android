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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.components.LangPill
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private data class BasketItem(
    val name: String,
    val detail: String,
    val farmer: String,
    val region: String,
    val price: Int,
    val mallPrice: Int,
    val tint: Color,
)

private val aiBasketItems = listOf(
    BasketItem("Sukuma wiki · 3 bunches", "Sukuma wiki · 3 bunches", "Joseph", "Kiambu", 75, 135, Color(0xFFE4E8DD)),
    BasketItem("Tomatoes · 2 kg", "Nyanya · 2 kg", "Wanjiku", "Limuru", 90, 180, Color(0xFFE8E6DF)),
    BasketItem("Unga wa mahindi · 2 kg", "Unga wa mahindi · 2 kg", "Mary", "Nyandarua", 220, 290, Color(0xFFEAE6D8)),
)

// S4 — AI Basket / Chat + voice
@Composable
fun AiChatScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ConsumerBg)
            .statusBarsPadding(),
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = BoardInk.copy(alpha = 0.07f), shape = RoundedCornerShape(0.dp))
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.14f), RoundedCornerShape(12.dp))
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = BoardInk,
                    modifier = Modifier.size(18.dp),
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (lang == "EN") "AI Basket" else "Kikapu cha AI",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
                Text(
                    text = if (lang == "EN") "prices from 41 live listings" else "bei kutoka orodha 41",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
            }
            LangPill(selected = lang, onSelect = onLangChange, onDark = false)
        }

        // Chat area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            // User bubble
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 5.dp))
                        .background(BoardInk)
                        .padding(horizontal = 16.dp, vertical = 13.dp),
                ) {
                    Text(
                        text = "Nataka sukuma na nyanya kwa watu wanne, bajeti 500 bob",
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 1.45.em),
                        color = Cream,
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // AI indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(BoardInk),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AutoAwesome,
                        contentDescription = null,
                        tint = FarmLime,
                        modifier = Modifier.size(11.dp),
                    )
                }
                Text(
                    text = if (lang == "EN") "Found the best prices" else "Nimepata bei nafuu zaidi",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
            }

            Spacer(Modifier.height(10.dp))

            // AI response card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.09f), RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .padding(15.dp),
            ) {
                Text(
                    text = if (lang == "EN")
                        "Basket for 4 people — KES 385, within budget:"
                    else
                        "Kikapu kwa watu 4 — KES 385, ndani ya bajeti:",
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 1.5.em),
                    color = BoardInk,
                )

                Spacer(Modifier.height(14.dp))

                aiBasketItems.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(item.tint),
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (lang == "EN") item.detail else item.detail,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = BoardInk,
                            )
                            Text(
                                text = "${item.farmer}, ${item.region}",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                                color = TextMuted,
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "KES ${item.price}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                                color = BoardInk,
                            )
                            Text(
                                text = "${item.mallPrice}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    textDecoration = TextDecoration.LineThrough,
                                ),
                                color = TextMuted,
                            )
                        }
                    }
                    Spacer(Modifier.height(11.dp))
                }

                // Savings row + Add all
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = if (lang == "EN") "YOU SAVE" else "UNAOKOA",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 0.06.em,
                                fontSize = 10.sp,
                            ),
                            color = TextMuted,
                        )
                        Text(
                            text = "KES 220",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                            ),
                            color = SavingsOrange,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(BuyerGreen)
                            .clickable { navController.navigate(Screen.Cart.route) }
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                    ) {
                        Text(
                            text = if (lang == "EN") "Add all" else "Ongeza zote",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White,
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            // Suggestion chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                listOf(
                    if (lang == "EN") "Add meat" else "Ongeza nyama",
                    if (lang == "EN") "Cheaper option" else "Chaguo nafuu",
                    if (lang == "EN") "Week under 2000" else "Wiki chini 2000",
                ).forEach { chip ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .border(1.dp, BoardInk.copy(alpha = 0.12f), RoundedCornerShape(999.dp))
                            .background(Color.White)
                            .padding(horizontal = 13.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = chip,
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = BoardInk,
                        )
                    }
                }
            }
        }

        // Input bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = BoardInk.copy(alpha = 0.07f), shape = RoundedCornerShape(0.dp))
                .background(Color.White)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(LightSurface)
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = if (lang == "EN") "Type or speak…" else "Andika au sema…",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted,
                )
            }
            Spacer(Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(BoardInk),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = "Voice",
                    tint = FarmLime,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}
