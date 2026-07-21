package com.inkp2ny.smartinkopslista.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.inkp2ny.smartinkopslista.ui.theme.Dimens

@Composable
fun ShareSyncTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.SpacingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall, Alignment.CenterVertically),
    ) {
        Text(text = "👥", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Synk & Dela kommer snart", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "Här kommer du kunna dela inköpslistan med familj och vänner i realtid.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}
