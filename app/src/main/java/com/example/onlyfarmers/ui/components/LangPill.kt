package com.example.onlyfarmers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onlyfarmers.ui.theme.BoardInk
import com.example.onlyfarmers.ui.theme.Cream

@Composable
fun LangPill(
    selected: String,
    onSelect: (String) -> Unit,
    onDark: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val pillBg = if (onDark) Cream.copy(alpha = 0.08f) else BoardInk.copy(alpha = 0.06f)
    val activeBg = if (onDark) Cream else BoardInk
    val activeText = if (onDark) BoardInk else Cream
    val inactiveText = if (onDark) Cream.copy(alpha = 0.6f) else BoardInk.copy(alpha = 0.5f)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(pillBg)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        listOf("EN", "SW").forEach { lang ->
            val active = lang == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (active) activeBg else Color.Transparent)
                    .clickable { onSelect(lang) }
                    .padding(horizontal = 13.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = lang,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (active) activeText else inactiveText,
                    fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
                )
            }
        }
    }
}
