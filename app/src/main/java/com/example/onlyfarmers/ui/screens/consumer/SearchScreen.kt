package com.example.onlyfarmers.ui.screens.consumer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.TrendingUp
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private val searchCategories = listOf("All", "Vegetables", "Fruit", "Grains", "Dairy", "Herbs")

private data class SearchResult(
    val name: String,
    val nameSw: String,
    val price: Int,
    val mallPrice: Int,
    val farmer: String,
    val region: String,
    val emoji: String,
)

private val allResults = listOf(
    SearchResult("Tomatoes", "Nyanya", 45, 80, "Joseph M.", "Kiambu", "🍅"),
    SearchResult("Kale", "Sukuma wiki", 18, 35, "Amina W.", "Nakuru", "🥬"),
    SearchResult("Avocado", "Parachichi", 25, 60, "Peter K.", "Murang'a", "🥑"),
    SearchResult("Carrots", "Karoti", 30, 55, "Grace N.", "Nyandarua", "🥕"),
    SearchResult("Bananas", "Ndizi", 20, 40, "Moses A.", "Kisii", "🍌"),
    SearchResult("Spinach", "Mchicha", 15, 28, "Sarah J.", "Kiambu", "🌿"),
)

// S11 — Search · browse by category + keyword
@Composable
fun SearchScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val results = allResults.filter { r ->
        val matchesQuery = query.isEmpty() ||
            r.name.contains(query, ignoreCase = true) ||
            r.nameSw.contains(query, ignoreCase = true)
        val matchesCat = selectedCategory == "All" ||
            (selectedCategory == "Vegetables" && r.emoji in listOf("🍅", "🥬", "🥕", "🌿")) ||
            (selectedCategory == "Fruit" && r.emoji in listOf("🥑", "🍌")) ||
            true.also { } // other cats show all for now
        matchesQuery
    }

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
                    text = if (lang == "EN") "Search" else "Tafuta",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = false)
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
                                text = if (lang == "EN") "Tomatoes, sukuma, avocado…" else "Nyanya, sukuma, parachichi…",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextMuted,
                            )
                        }
                        inner()
                    },
                )
            }

            Spacer(Modifier.height(16.dp))

            // Category chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(searchCategories) { cat ->
                    val active = cat == selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(if (active) BoardInk else LightSurface)
                            .clickable { selectedCategory = cat }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                            ),
                            color = if (active) Color.White else TextSubtle,
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            if (query.isEmpty()) {
                // Trending section when no query
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(Icons.Rounded.TrendingUp, null, tint = TextMuted, modifier = Modifier.size(14.dp))
                    Text(
                        text = if (lang == "EN") "TRENDING NOW" else "INAYOVUMA SASA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.09.em,
                            fontSize = 10.sp,
                        ),
                        color = TextMuted,
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            // Results grid — .chunked(2) to avoid nested scroll
            val chunked = results.chunked(2)
            chunked.forEach { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    pair.forEach { item ->
                        SearchCard(
                            item = item,
                            lang = lang,
                            onClick = { navController.navigate(Screen.ProductDetail.createRoute(item.name)) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }

            if (results.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("🔍", fontSize = 40.sp)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = if (lang == "EN") "Nothing found for \"$query\""
                               else "Hakuna matokeo ya \"$query\"",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted,
                    )
                }
            }
        }

        ConsumerBottomNav(
            activeTab = ConsumerTab.Search,
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun SearchCard(item: SearchResult, lang: String, onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    val discount = ((item.mallPrice - item.price) * 100 / item.mallPrice)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(LightSurface)
            .clickable(onClick = onClick)
            .padding(14.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE8E5DC)),
            contentAlignment = Alignment.Center,
        ) {
            Text(item.emoji, fontSize = 36.sp)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(SavingsOrange)
                    .padding(horizontal = 5.dp, vertical = 2.dp),
            ) {
                Text(
                    text = "-$discount%",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                    ),
                    color = Color.White,
                )
            }
        }
        Spacer(Modifier.height(9.dp))
        Text(
            text = if (lang == "EN") item.name else item.nameSw,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = BoardInk,
        )
        Spacer(Modifier.height(3.dp))
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = "KES ${item.price}",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                ),
                color = BuyerGreen,
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
        Spacer(Modifier.height(3.dp))
        Text(
            text = "${item.farmer} · ${item.region}",
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextMuted,
            maxLines = 1,
        )
    }
}
