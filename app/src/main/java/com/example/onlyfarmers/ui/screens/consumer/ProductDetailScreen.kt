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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.VerifiedUser
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.BuyerGreen
import com.example.onlyfarmers.ui.theme.ConsumerBg
import com.example.onlyfarmers.ui.theme.LightSurface
import com.example.onlyfarmers.ui.theme.SavingsOrange
import com.example.onlyfarmers.ui.theme.TextMuted
import com.example.onlyfarmers.ui.theme.TextSubtle

private data class ProductInfo(
    val name: String,
    val nameSw: String,
    val emoji: String,
    val price: Int,
    val unit: String,
    val mallPrice: Int,
    val discount: Int,
    val farmer: String,
    val farmerRegion: String,
    val farmerOrders: Int,
    val farmerRating: String,
    val heroBg: Color,
    val about: String,
    val aboutSw: String,
)

private val catalogue = mapOf(
    "Tomatoes" to ProductInfo("Tomatoes", "Nyanya", "🍅", 45, "kg", 90, 50, "Joseph Mwangi", "Kiambu", 312, "4.9", Color(0xFFF2EAE3), "Grade A tomatoes grown without chemical fertilisers. Hand-picked at peak ripeness and delivered same morning.", "Nyanya za Grade A zinazolimwa bila mbolea za kemikali. Zinaokotwa mkono wakati wa kukomaa na kuwasilishwa asubuhi."),
    "Sukuma wiki" to ProductInfo("Kale", "Sukuma wiki", "🥬", 25, "bunch", 45, 44, "Joseph Mwangi", "Kiambu", 312, "4.9", Color(0xFFE6EEE1), "Freshly harvested sukuma wiki, delivered within hours of cutting. No wilting, no wax coating.", "Sukuma wiki iliovunwa sasa hivi, inawasilishwa ndani ya masaa. Haijakauka, haijapakwa nta."),
    "Potatoes" to ProductInfo("Potatoes", "Viazi", "🥔", 70, "kg", 110, 36, "Mary Wanjiku", "Nyandarua", 198, "4.7", Color(0xFFEDE8DA), "Desiree and Dutch Robjin varieties. Stored in cool sheds, no sprouting. Perfect for frying or boiling.", "Aina za Desiree na Dutch Robjin. Zimehifadhiwa katika ghala baridi, hazijaota. Nzuri kwa kukaanga au kupika."),
    "Red onions" to ProductInfo("Red Onions", "Vitunguu", "🧅", 85, "kg", 140, 39, "Halima Abdi", "Naivasha", 156, "4.8", Color(0xFFEDE0DA), "Sun-dried Bombay red onions from Naivasha. Dense, pungent, and long-shelf-life. No sprout inhibitors used.", "Vitunguu vya aina ya Bombay vilivyokaushwa jua kutoka Naivasha. Vikali na vya kudumu. Hakuna dawa za kuzuia kuota."),
    "Avocado" to ProductInfo("Avocado", "Parachichi", "🥑", 25, "piece", 60, 58, "Peter Kamau", "Murang'a", 440, "5.0", Color(0xFFE3EDDF), "Hass avocados from Murang'a highlands. Harvested at stage 5 ripeness — ready in 2 days at room temperature.", "Parachichi za aina ya Hass kutoka milima ya Murang'a. Zinashindwa hatua ya 5 — tayari ndani ya siku 2 joto la kawaida."),
    "Carrots" to ProductInfo("Carrots", "Karoti", "🥕", 30, "kg", 55, 45, "Grace Njoki", "Nyandarua", 267, "4.8", Color(0xFFF2E8DC), "Nantes variety carrots, washed and topped. Sweet, crunchy, and naturally orange. No artificial colouring.", "Karoti za aina ya Nantes, ziliosafishwa. Tamu, ngumu, na rangi ya asili. Hakuna rangi ya bandia."),
)

private val defaultProduct = ProductInfo("Produce", "Mazao", "🌿", 45, "kg", 90, 50, "Joseph Mwangi", "Kiambu", 312, "4.9", Color(0xFFE6EEE1), "Fresh farm produce delivered directly from the shamba.", "Mazao mapya yanayowasilishwa moja kwa moja kutoka shambani.")

// S14 — Product Detail
@Composable
fun ProductDetailScreen(navController: NavController, productName: String, lang: String, onLangChange: (String) -> Unit) {
    val product = catalogue[productName] ?: defaultProduct
    var qty by remember { mutableIntStateOf(1) }
    val total = qty * product.price

    Box(modifier = Modifier.fillMaxSize().background(ConsumerBg)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp),
        ) {
            // Hero
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(product.heroBg),
            ) {
                // Back button
                Box(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(16.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.85f))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Rounded.ArrowBack, "Back", tint = BoardInk, modifier = Modifier.size(20.dp))
                }

                // Discount badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .statusBarsPadding()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(SavingsOrange)
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = "-${product.discount}%",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Color.White,
                    )
                }

                // Emoji
                Text(
                    text = product.emoji,
                    fontSize = 96.sp,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            Column(modifier = Modifier.padding(20.dp)) {
                // Name + price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (lang == "EN") product.name else product.nameSw,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.02).em,
                            ),
                            color = BoardInk,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (lang == "EN") "AI FIXED RATE · NO BARGAINING"
                                   else "BEI YA KAWAIDA · HAKUNA KUBARGAIN",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.08.em,
                                fontSize = 10.sp,
                            ),
                            color = BuyerGreen,
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "KES ${product.price}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.ExtraBold,
                            ),
                            color = BoardInk,
                        )
                        Text(
                            text = "/${product.unit}",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextMuted,
                        )
                        Text(
                            text = "KES ${product.mallPrice}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = FontFamily.Monospace,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                            color = TextMuted,
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                // Farmer card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(LightSurface)
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDCD9CC)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("👨‍🌾", fontSize = 22.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.farmer,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = BoardInk,
                        )
                        Text(
                            text = product.farmerRegion,
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextMuted,
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            Icon(Icons.Rounded.Star, null, tint = Color(0xFFE8A020), modifier = Modifier.size(13.dp))
                            Text(
                                text = product.farmerRating,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = BoardInk,
                            )
                        }
                        Text(
                            text = "${product.farmerOrders} orders",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextMuted,
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Quality badges
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    QualityBadge(text = if (lang == "EN") "Grade A" else "Daraja A")
                    QualityBadge(text = if (lang == "EN") "AI weighed" else "AI imepima")
                    QualityBadge(text = if (lang == "EN") "Pesticide-free" else "Bila dawa")
                }

                Spacer(Modifier.height(20.dp))

                // About
                Text(
                    text = if (lang == "EN") "About this produce" else "Kuhusu mazao haya",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                    color = BoardInk,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (lang == "EN") product.about else product.aboutSw,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 1.6.em),
                    color = TextSubtle,
                )

                Spacer(Modifier.height(24.dp))

                // Quantity stepper
                Text(
                    text = if (lang == "EN") "HOW MANY ${product.unit.uppercase()}?" else "KIASI GANI?",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.10.em,
                        fontSize = 10.sp,
                    ),
                    color = TextMuted,
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(1.dp, BoardInk.copy(alpha = 0.14f), RoundedCornerShape(14.dp))
                            .background(LightSurface)
                            .clickable { if (qty > 1) qty-- },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Rounded.Remove, null, tint = BoardInk, modifier = Modifier.size(22.dp))
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "$qty",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 1.em,
                            ),
                            color = BoardInk,
                        )
                        Text(
                            text = product.unit,
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextMuted,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(BoardInk)
                            .clickable { qty++ },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Rounded.Add, null, tint = Color.White, modifier = Modifier.size(22.dp))
                    }
                }
            }
        }

        // Sticky CTA
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(ConsumerBg)
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (lang == "EN") "Total" else "Jumla",
                    style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                    color = TextMuted,
                )
                Text(
                    text = "KES $total",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = BoardInk,
                )
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BoardInk)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = if (lang == "EN") "Add to basket" else "Weka kikapuni",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
private fun QualityBadge(text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFD8D5CC), RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(horizontal = 9.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(Icons.Rounded.VerifiedUser, null, tint = BuyerGreen, modifier = Modifier.size(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
            ),
            color = BoardInk,
        )
    }
}
