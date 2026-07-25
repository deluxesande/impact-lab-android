package com.example.onlyfarmers.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private data class FaqItem(val questionEN: String, val questionSW: String, val answerEN: String, val answerSW: String)

private val faqs = listOf(
    FaqItem(
        questionEN = "How are prices set?",
        questionSW = "Bei zinawekwaje?",
        answerEN = "Prices are set by OnlyFarmers based on current market data and transport costs. The rate is fixed — no bargaining, no broker markup. What you see is what farmers earn.",
        answerSW = "Bei zinawekwa na OnlyFarmers kwa kutumia data za soko na gharama za usafirishaji. Bei ni ya kawaida — hakuna kubargain, hakuna malipo ya dalali. Unachokiona ndicho mkulima anapata.",
    ),
    FaqItem(
        questionEN = "How do I place an order?",
        questionSW = "Ninawezaje kuweka oda?",
        answerEN = "Browse produce on the Home tab, tap a product to see details, then tap 'Add to basket'. Review your basket, choose a delivery slot and payment method on the Checkout screen, then confirm.",
        answerSW = "Angalia mazao kwenye kichupo cha Nyumbani, gusa bidhaa kuona maelezo, kisha gusa 'Weka kikapuni'. Kagua kikapu chako, chagua nafasi ya uwasilishaji na njia ya malipo, kisha thibitisha.",
    ),
    FaqItem(
        questionEN = "What delivery areas do you cover?",
        questionSW = "Mnafika maeneo gani?",
        answerEN = "We currently deliver to Nairobi (all zones), Kiambu, and Machakos town. More areas are coming soon. You can check coverage when you enter your address at checkout.",
        answerSW = "Tunafanya uwasilishaji Nairobi (maeneo yote), Kiambu, na mji wa Machakos. Maeneo zaidi yanakuja hivi karibuni. Unaweza kuangalia upatikanaji unapoingiza anwani yako.",
    ),
    FaqItem(
        questionEN = "How do I track my delivery?",
        questionSW = "Nawezaje kufuatilia uwasilishaji wangu?",
        answerEN = "After your order is confirmed, go to the Orders tab and tap your order. You'll see a live status timeline and a map view when the rider is assigned.",
        answerSW = "Baada ya oda yako kuthibitishwa, nenda kwenye kichupo cha Oda na uguse oda yako. Utaona mstari wa hali ya moja kwa moja na ramani unapopewa boda boda.",
    ),
    FaqItem(
        questionEN = "How does AI Basket work?",
        questionSW = "Kikapu cha AI kinafanyaje kazi?",
        answerEN = "Tap the AI Basket on the Home screen and tell it what you're cooking — in Swahili, Sheng, or English. It will suggest the produce you need, compare prices, and fill your basket automatically.",
        answerSW = "Gusa Kikapu cha AI kwenye skrini ya Nyumbani na useme unapika nini — kwa Kiswahili, Sheng, au Kiingereza. Itapendekeza mazao unayohitaji, kulinganisha bei, na kujaza kikapu chako kiotomatiki.",
    ),
    FaqItem(
        questionEN = "How do I sell on OnlyFarmers?",
        questionSW = "Ninawezaje kuuza kwenye OnlyFarmers?",
        answerEN = "Sign up as a Farmer on the welcome screen. List your produce with photos and quantity — AI will help you weigh and grade it. Once approved, buyers in your area will see your listing.",
        answerSW = "Jisajili kama Mkulima kwenye skrini ya karibu. Orodhesha mazao yako na picha na kiasi — AI itakusaidia kupima na kupanga daraja. Baada ya kuidhinishwa, wanunuzi katika eneo lako watakiona.",
    ),
)

@Composable
fun HelpFAQScreen(navController: NavController, lang: String) {
    var expandedIndex by remember { mutableIntStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
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
                        .background(LightSurface)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.ArrowBack, "Back", tint = BoardInk, modifier = Modifier.size(18.dp))
                }
                Column {
                    Text(
                        text = if (lang == "EN") "Help & FAQ" else "Msaada & Maswali",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = BoardInk,
                    )
                    Text(
                        text = if (lang == "EN") "${faqs.size} questions answered" else "Maswali ${faqs.size} yamejibiwa",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextMuted,
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // FAQ accordion
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                faqs.forEachIndexed { idx, item ->
                    val expanded = idx == expandedIndex
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (expanded) BuyerGreen.copy(alpha = 0.07f) else LightSurface),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedIndex = if (expanded) -1 else idx
                                }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Text(
                                text = if (lang == "EN") item.questionEN else item.questionSW,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (expanded) FontWeight.Bold else FontWeight.SemiBold,
                                ),
                                color = if (expanded) BuyerGreen else BoardInk,
                                modifier = Modifier.weight(1f),
                            )
                            Icon(
                                imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                contentDescription = null,
                                tint = if (expanded) BuyerGreen else TextMuted,
                                modifier = Modifier.size(20.dp),
                            )
                        }
                        AnimatedVisibility(
                            visible = expanded,
                            enter = expandVertically(),
                            exit = shrinkVertically(),
                        ) {
                            Text(
                                text = if (lang == "EN") item.answerEN else item.answerSW,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    lineHeight = 1.6.em,
                                ),
                                color = TextSubtle,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Contact card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(LightSurface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = if (lang == "EN") "Still need help?" else "Bado unahitaji msaada?",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = BoardInk,
                )
                Text(
                    text = if (lang == "EN") "WhatsApp: +254 700 123 456" else "WhatsApp: +254 700 123 456",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
                Text(
                    text = "support@onlyfarmers.co.ke",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = BuyerGreen,
                )
            }
        }
    }
}
