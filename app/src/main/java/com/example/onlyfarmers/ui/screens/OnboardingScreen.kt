package com.example.onlyfarmers.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Agriculture
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.ShoppingBag
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlyfarmers.ui.navigation.Screen
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream
import com.example.onlyfarmers.ui.theme.FarmLime
import com.example.onlyfarmers.ui.theme.TextOnDarkMuted

private data class OnboardSlide(
    val bg: Color,
    val accentBg: Color,
    val icon: ImageVector,
    val iconTint: Color,
    val label: String,
    val labelSw: String,
    val headline: String,
    val headlineSw: String,
    val body: String,
    val bodySw: String,
    val headlineColor: Color,
    val bodyColor: Color,
)

private val slides = listOf(
    OnboardSlide(
        bg = BoardInk,
        accentBg = FarmLime,
        icon = Icons.Rounded.ShoppingBag,
        iconTint = BoardInk,
        label = "FOR BUYERS",
        labelSw = "KWA WANUNUZI",
        headline = "Groceries below\nmall price.",
        headlineSw = "Mboga chini ya\nbei ya duka.",
        body = "Farm-fresh produce delivered to your door — 30–60% cheaper than Naivas or Carrefour. Direct from the shamba.",
        bodySw = "Mazao mapya ya shambani yanawasilishwa mlangoni mwako — asilimia 30–60 nafuu kuliko Naivas au Carrefour.",
        headlineColor = Cream,
        bodyColor = Color(0xFF9AA78A),
    ),
    OnboardSlide(
        bg = FarmLime,
        accentBg = BoardInk,
        icon = Icons.Rounded.Agriculture,
        iconTint = FarmLime,
        label = "FOR FARMERS",
        labelSw = "KWA WAKULIMA",
        headline = "Fixed rates.\nNo brokers.",
        headlineSw = "Bei imara.\nHakuna madalali.",
        body = "Photograph your produce, get an AI-set rate, and receive M-Pesa the same day. No middlemen cutting your earnings.",
        bodySw = "Piga picha mazao yako, pata bei iliyowekwa na AI, na upokee M-Pesa siku hiyo hiyo. Hakuna wadudu wanaokula mapato yako.",
        headlineColor = BoardInk,
        bodyColor = BoardInk.copy(alpha = 0.65f),
    ),
    OnboardSlide(
        bg = BoardInk,
        accentBg = Color(0xFF2A3F1A),
        icon = Icons.Rounded.AutoAwesome,
        iconTint = FarmLime,
        label = "AI BASKET",
        labelSw = "KIKAPU CHA AI",
        headline = "Tell it what\nyou need.",
        headlineSw = "Mwambie\nunachohitaji.",
        body = "\"Weekly greens for 4\" — the AI builds your basket, finds the cheapest farms, and books delivery. Done.",
        bodySw = "\"Mboga ya wiki kwa watu 4\" — AI inajenga kikapu chako, inatafuta mashamba ya bei nafuu, na inaweka uwasilishaji. Imekwisha.",
        headlineColor = Cream,
        bodyColor = Color(0xFF9AA78A),
    ),
)

// Onboarding — 3 value-prop slides before role selection
@Composable
fun OnboardingScreen(navController: NavController, lang: String) {
    var page by remember { mutableIntStateOf(0) }
    val slide = slides[page]
    val isLast = page == slides.lastIndex

    Box(modifier = Modifier.fillMaxSize().background(slide.bg)) {
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                (slideInHorizontally(tween(320)) { it / 3 } + fadeIn(tween(320))) togetherWith
                    (slideOutHorizontally(tween(280)) { -it / 3 } + fadeOut(tween(200)))
            },
            label = "slide",
        ) { p ->
            val s = slides[p]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(horizontal = 28.dp),
            ) {
                Spacer(Modifier.weight(0.6f))

                // Icon mark
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(s.accentBg),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(s.icon, null, tint = s.iconTint, modifier = Modifier.size(38.dp))
                }

                Spacer(Modifier.height(28.dp))

                // Label
                Text(
                    text = if (lang == "EN") s.label else s.labelSw,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.10.em,
                        fontSize = 10.sp,
                    ),
                    color = if (s.bg == FarmLime) BoardInk.copy(alpha = 0.55f) else TextOnDarkMuted,
                )

                Spacer(Modifier.height(12.dp))

                // Headline
                Text(
                    text = if (lang == "EN") s.headline else s.headlineSw,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.02).em,
                        lineHeight = 1.05.em,
                    ),
                    color = s.headlineColor,
                )

                Spacer(Modifier.height(18.dp))

                // Body
                Text(
                    text = if (lang == "EN") s.body else s.bodySw,
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 1.55.em),
                    color = s.bodyColor,
                )

                Spacer(Modifier.weight(1f))
            }
        }

        // Bottom controls
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 28.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // Dot indicators
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                slides.indices.forEach { i ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(if (i == page) 22.dp else 7.dp, 7.dp)
                            .background(
                                if (i == page)
                                    if (slide.bg == FarmLime) BoardInk else FarmLime
                                else
                                    if (slide.bg == FarmLime) BoardInk.copy(alpha = 0.22f) else Cream.copy(alpha = 0.22f)
                            ),
                    )
                }
            }

            // CTA row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Skip / Back
                if (page == 0) {
                    Text(
                        text = if (lang == "EN") "Skip" else "Ruka",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = if (slide.bg == FarmLime) BoardInk.copy(alpha = 0.55f) else TextOnDarkMuted,
                        modifier = Modifier
                            .clickable { navController.navigate(Screen.Splash.route) { popUpTo(Screen.Onboarding.route) { inclusive = true } } }
                            .padding(vertical = 4.dp),
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(
                                if (slide.bg == FarmLime) BoardInk.copy(alpha = 0.12f)
                                else Cream.copy(alpha = 0.10f)
                            )
                            .clickable { page-- },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "←",
                            style = MaterialTheme.typography.titleLarge,
                            color = if (slide.bg == FarmLime) BoardInk else Cream,
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                // Next / Get started
                Box(
                    modifier = Modifier
                        .height(52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (slide.bg == FarmLime) BoardInk
                            else FarmLime
                        )
                        .clickable {
                            if (isLast) {
                                navController.navigate(Screen.Splash.route) {
                                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                                }
                            } else {
                                page++
                            }
                        }
                        .padding(horizontal = 24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = if (isLast) {
                            if (lang == "EN") "Get started" else "Anza"
                        } else {
                            if (lang == "EN") "Next →" else "Endelea →"
                        },
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                        color = if (slide.bg == FarmLime) FarmLime else BoardInk,
                    )
                }
            }
        }
    }
}
