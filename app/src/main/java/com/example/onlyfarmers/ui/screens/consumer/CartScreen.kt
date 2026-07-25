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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

private data class CartItem(
    val name: String,
    val nameKi: String,
    val price: Int,
    val mallPrice: Int,
    val unit: String,
    val tint: Color,
)

private val cartItems = listOf(
    CartItem("Tomatoes", "Nyanya", 90, 180, "kg", Color(0xFFE8E6DF)),
    CartItem("Sukuma wiki", "Sukuma wiki", 75, 135, "bunches", Color(0xFFE4E8DD)),
)

// S5 — Basket · savings + swap
@Composable
fun CartScreen(navController: NavController, lang: String, onLangChange: (String) -> Unit) {
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
            Text(
                text = if (lang == "EN") "Your basket" else "Kikapu chako",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = BoardInk,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = if (lang == "EN") "3 items" else "vitu 3",
                style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                color = TextMuted,
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp),
        ) {
            // Savings banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(BoardInk)
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = if (lang == "EN") "TOTAL SAVED VS MALL" else "UMEOKOA DHIDI YA DUKA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 0.08.em,
                            fontSize = 10.sp,
                        ),
                        color = FarmLime.copy(alpha = 0.8f),
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "KES 640",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 1.1.em,
                        ),
                        color = FarmLime,
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "38% less",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = Cream.copy(alpha = 0.6f),
                    )
                    Text(
                        text = "than Sarit",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = Cream.copy(alpha = 0.6f),
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            // Cart items
            cartItems.forEach { item ->
                CartItemRow(item = item, lang = lang)
                Spacer(Modifier.height(11.dp))
            }

            // AI swap suggestion (dashed green border)
            val swapBorderColor = BuyerGreen
            val swapCornerRadius = 16.dp
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRoundRect(
                            color = swapBorderColor,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(swapCornerRadius.toPx()),
                            style = Stroke(
                                width = 1.5.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f),
                            ),
                        )
                    }
                    .clip(RoundedCornerShape(swapCornerRadius))
                    .background(BuyerGreen.copy(alpha = 0.05f))
                    .padding(14.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AutoAwesome,
                        contentDescription = null,
                        tint = BuyerGreen,
                        modifier = Modifier.size(13.dp),
                    )
                    Text(
                        text = "AI SUGGESTED SWAP",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.10.em,
                            fontSize = 9.5.sp,
                        ),
                        color = BuyerGreen,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (lang == "EN")
                        "Unga wa mahindi is out at Mary's. Halima has it at KES 195 — 25 bob cheaper, arrives same day."
                    else
                        "Unga wa mahindi umeisha kwa Mary. Halima ana kwa KES 195 — 25 bob nafuu, inafika siku hiyo hiyo.",
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 1.5.em),
                    color = BoardInk,
                )
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(BuyerGreen),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (lang == "EN") "Swap it" else "Badilisha",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(42.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .border(1.dp, BoardInk.copy(alpha = 0.14f), RoundedCornerShape(11.dp))
                            .background(Color.White)
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (lang == "EN") "Keep" else "Weka",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = BoardInk,
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Order summary
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SummaryRow(label = if (lang == "EN") "Subtotal" else "Jumla ndogo", value = "KES 385")
                SummaryRow(label = if (lang == "EN") "Delivery · batched" else "Utoaji · pamoja", value = "KES 80")
                SummaryRow(
                    label = if (lang == "EN") "Vendor payout" else "Malipo ya muuzaji",
                    value = "KES 340 direct",
                    valueColor = BuyerGreen,
                )
            }

            Spacer(Modifier.height(80.dp))
        }

        // Sticky CTA
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 18.dp, vertical = 14.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BuyerGreen)
                    .clickable { navController.navigate(Screen.Checkout.route) }
                    .padding(horizontal = 22.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = if (lang == "EN") "Place order" else "Weka agizo",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                )
                Text(
                    text = "KES 465",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Color.White,
                )
            }
            Spacer(Modifier.height(9.dp))
            Text(
                text = if (lang == "EN") "M-Pesa · pay on delivery" else "M-Pesa · lipa unapopokea",
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                color = TextMuted,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
private fun CartItemRow(item: CartItem, lang: String) {
    var qty by remember { mutableIntStateOf(2) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, BoardInk.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(item.tint),
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (lang == "EN") item.name else item.nameKi,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = BoardInk,
            )
            Spacer(Modifier.height(3.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.Bottom) {
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
        // Qty stepper
        Row(
            modifier = Modifier
                .border(1.dp, BoardInk.copy(alpha = 0.14f), RoundedCornerShape(999.dp))
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(LightSurface)
                    .clickable { if (qty > 1) qty-- },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Rounded.Remove, contentDescription = "Decrease", tint = BoardInk, modifier = Modifier.size(16.dp))
            }
            Text(
                text = "$qty ${item.unit}",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                ),
                color = BoardInk,
            )
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(BoardInk)
                    .clickable { qty++ },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Increase", tint = Cream, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String, valueColor: Color = BoardInk) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
            color = TextSubtle,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
            ),
            color = valueColor,
        )
    }
}
