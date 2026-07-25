package com.example.onlyfarmers.ui.screens.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
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
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private enum class ListingStatus { Live, Sold, Expired }

private data class Listing(
    val emoji: String,
    val name: String,
    val nameSw: String,
    val qty: Int,
    val unit: String,
    val pricePerUnit: Int,
    val status: ListingStatus,
    val date: String,
    val ordersCount: Int,
    val earnings: Int,
)

private val allListings = listOf(
    Listing("🍅", "Tomatoes", "Nyanya", 120, "kg", 45, ListingStatus.Live, "Today", 2, 540),
    Listing("🥬", "Kale", "Sukuma wiki", 80, "bunches", 25, ListingStatus.Live, "Today", 1, 200),
    Listing("🥔", "Potatoes", "Viazi", 200, "kg", 70, ListingStatus.Sold, "23 Jul", 3, 1400),
    Listing("����", "Red Onions", "Vitunguu", 50, "kg", 85, ListingStatus.Sold, "20 Jul", 1, 850),
    Listing("🥕", "Carrots", "Karoti", 60, "kg", 30, ListingStatus.Expired, "15 Jul", 0, 0),
    Listing("🌿", "Spinach", "Mchicha", 40, "bunches", 15, ListingStatus.Sold, "18 Jul", 2, 600),
)

private val filterTabs = listOf("All", "Live", "Sold", "Expired")

// S20 — Farmer Listings · manage active + history
@Composable
fun FarmerListingsScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
    var selectedFilter by remember { mutableStateOf("All") }

    val filtered = when (selectedFilter) {
        "Live" -> allListings.filter { it.status == ListingStatus.Live }
        "Sold" -> allListings.filter { it.status == ListingStatus.Sold }
        "Expired" -> allListings.filter { it.status == ListingStatus.Expired }
        else -> allListings
    }

    val totalEarnings = allListings.filter { it.status == ListingStatus.Sold }.sumOf { it.earnings }

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
                Text(
                    text = if (lang == "EN") "My Listings" else "Orodha Zangu",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Cream,
                    modifier = Modifier.weight(1f),
                )
                LangPill(selected = lang, onSelect = onLangChange, onDark = true)
            }

            // Stats strip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Cream.copy(alpha = 0.08f))
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ListingStat(
                    value = "${allListings.count { it.status == ListingStatus.Live }}",
                    label = if (lang == "EN") "live" else "inaendelea",
                    color = FarmLime,
                )
                Box(modifier = Modifier.width(1.dp).height(32.dp).background(Cream.copy(alpha = 0.12f)))
                ListingStat(
                    value = "${allListings.count { it.status == ListingStatus.Sold }}",
                    label = if (lang == "EN") "sold" else "imeuzwa",
                    color = Cream,
                )
                Box(modifier = Modifier.width(1.dp).height(32.dp).background(Cream.copy(alpha = 0.12f)))
                ListingStat(
                    value = "KES ${"%,d".format(totalEarnings)}",
                    label = if (lang == "EN") "earned" else "iliyopatikana",
                    color = Cream,
                )
            }

            Spacer(Modifier.height(16.dp))

            // Filter chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(filterTabs) { tab ->
                    val active = tab == selectedFilter
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(if (active) Cream else Cream.copy(alpha = 0.10f))
                            .clickable { selectedFilter = tab }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                            ),
                            color = if (active) BoardInk else TextOnDarkMuted,
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                filtered.forEach { listing ->
                    ListingCard(listing = listing, lang = lang)
                }

                if (filtered.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (lang == "EN") "No listings in this category"
                                   else "Hakuna orodha katika kundi hili",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextOnDarkMuted,
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
        }

        // FAB — new listing
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 88.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(FarmLime)
                .clickable { navController.navigate(Screen.NewListing.route) },
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Rounded.Add, null, tint = BoardInk, modifier = Modifier.size(26.dp))
        }

        FarmerBottomNav(
            activeTab = FarmerTab.Home,
            navController = navController,
            lang = lang,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun ListingCard(listing: Listing, lang: String) {
    val (statusBg, statusText, statusLabel, statusLabelSw) = when (listing.status) {
        ListingStatus.Live -> Quad(FarmLime.copy(alpha = 0.18f), FarmLime, "LIVE", "INAENDELEA")
        ListingStatus.Sold -> Quad(Cream.copy(alpha = 0.12f), TextOnDarkMuted, "SOLD", "IMEUZWA")
        ListingStatus.Expired -> Quad(SavingsOrange.copy(alpha = 0.12f), SavingsOrange, "EXPIRED", "IMEISHA")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Cream.copy(alpha = 0.07f))
            .border(1.dp, Cream.copy(alpha = if (listing.status == ListingStatus.Live) 0.18f else 0.08f), RoundedCornerShape(18.dp))
            .clickable { }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Cream.copy(alpha = 0.10f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(listing.emoji, fontSize = 24.sp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (lang == "EN") listing.name else listing.nameSw,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = Cream,
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = "${listing.qty} ${listing.unit} · KES ${listing.pricePerUnit}/unit",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                color = TextOnDarkMuted,
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = listing.date,
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace, fontSize = 10.sp),
                color = TextOnDarkMuted,
            )
        }
        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(statusBg)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = if (lang == "EN") statusLabel else statusLabelSw,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                    ),
                    color = statusText,
                )
            }
            if (listing.earnings > 0) {
                Text(
                    text = "+KES ${listing.earnings}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = FarmLime,
                )
            } else if (listing.status == ListingStatus.Live && listing.ordersCount > 0) {
                Text(
                    text = "${listing.ordersCount} order${if (listing.ordersCount > 1) "s" else ""}",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextOnDarkMuted,
                )
            }
        }
    }
}

@Composable
private fun ListingStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
            ),
            color = color,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextOnDarkMuted,
        )
    }
}

private data class Quad<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
