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
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Mic
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private data class Produce(
    val name: String,
    val nameKi: String,
    val price: Int,
    val unit: String,
    val mallPrice: Int,
    val farmer: String,
    val region: String,
    val savingsPct: Int?,
    val tint: Color,
)

private val sampleProduce = listOf(
    Produce("Tomatoes", "Nyanya", 45, "/kg", 90, "Wanjiku", "Limuru", 50, Color(0xFFE8E6DF)),
    Produce("Sukuma wiki", "Sukuma wiki", 25, "/bunch", 45, "Joseph", "Kiambu", 44, Color(0xFFE4E8DD)),
    Produce("Potatoes", "Viazi", 70, "/kg", 110, "Mary", "Nyandarua", null, Color(0xFFEAE6D8)),
    Produce("Red onions", "Vitunguu", 85, "/kg", 140, "Halima", "Naivasha", null, Color(0xFFE6E4DD)),
)

// S3 — Consumer Home / Browse
@Composable
fun HomeScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Vegetables", "Fruit", "Grains")

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 80.dp),
        ) {
            // Header: location + lang pill + avatar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (lang == "EN") "DELIVER TO" else "PELEKA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.08.em,
                        ),
                        color = TextMuted,
                    )
                    Spacer(Modifier.height(3.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Kilimani, Nairobi",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = BoardInk,
                        )
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
                LangPill(selected = lang, onSelect = onLangChange, onDark = false)
                Spacer(Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8E6DF)),
                )
            }

            // Search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, BoardInk.copy(alpha = 0.10f), RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .padding(horizontal = 14.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Rounded.Search, contentDescription = null, tint = TextMuted, modifier = Modifier.size(19.dp))
                Spacer(Modifier.width(10.dp))
                Text(
                    text = if (lang == "EN") "Sukuma, nyanya, unga…" else "Tafuta mboga, matunda…",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted,
                )
            }

            Spacer(Modifier.height(14.dp))

            // AI Basket banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Brush.linearGradient(colors = listOf(Color(0xFF131710), Color(0xFF26301C))))
                    .clickable { navController.navigate(Screen.AiChat.route) }
                    .padding(16.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AutoAwesome,
                                contentDescription = null,
                                tint = FarmLime,
                                modifier = Modifier.size(13.dp),
                            )
                            Text(
                                text = "AI BASKET",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.10.em,
                                    fontSize = 9.5.sp,
                                ),
                                color = FarmLime,
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = if (lang == "EN")
                                "Say what you're cooking — we'll fill the basket"
                            else
                                "Sema unapika nini — tutajaza kikapu",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                lineHeight = 1.25.em,
                            ),
                            color = Cream,
                        )
                        Spacer(Modifier.height(7.dp))
                        Text(
                            text = "Kiswahili · Sheng · voice",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = Cream.copy(alpha = 0.6f),
                        )
                    }
                    Spacer(Modifier.width(14.dp))
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(FarmLime),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Mic,
                            contentDescription = null,
                            tint = BoardInk,
                            modifier = Modifier.size(28.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            // Category chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                categories.forEach { cat ->
                    val active = cat == selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(if (active) BoardInk else Color.White)
                            .border(
                                width = if (active) 0.dp else 1.dp,
                                color = BoardInk.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(999.dp),
                            )
                            .clickable { selectedCategory = cat }
                            .padding(horizontal = 15.dp, vertical = 9.dp),
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = if (active) Cream else BoardInk,
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            // Grid heading + See all
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = if (lang == "EN") "Fresh near you" else "Karibu nawe",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.01).em,
                    ),
                    color = BoardInk,
                )
                Text(
                    text = if (lang == "EN") "See all" else "Ona zote",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = BuyerGreen,
                )
            }

            Spacer(Modifier.height(12.dp))

            // 2-col produce grid (no LazyGrid — outer column is already scrollable)
            sampleProduce.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    row.forEach { item ->
                        ProduceCard(item = item, lang = lang, modifier = Modifier.weight(1f))
                    }
                    if (row.size == 1) Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.height(12.dp))
            }
        }

        // Fixed bottom nav
        ConsumerBottomNav(
            activeTab = ConsumerTab.Home,
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun ProduceCard(item: Produce, lang: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, BoardInk.copy(alpha = 0.07f), RoundedCornerShape(16.dp))
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(item.tint),
        ) {
            if (item.savingsPct != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(SavingsOrange)
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                ) {
                    Text(
                        text = "−${item.savingsPct}%",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                        ),
                        color = Color.White,
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(start = 11.dp, end = 11.dp, top = 9.dp, bottom = 10.dp)) {
            Text(
                text = if (lang == "EN") item.name else item.nameKi,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = BoardInk,
            )
            Spacer(Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "KES ${item.price}",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    ),
                    color = BoardInk,
                )
                Text(
                    text = item.unit,
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
            }
            Text(
                text = "KES ${item.mallPrice} at mall",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    textDecoration = TextDecoration.LineThrough,
                ),
                color = TextMuted,
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = "${item.farmer} · ${item.region}",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                color = TextSubtle,
            )
        }
    }
}
