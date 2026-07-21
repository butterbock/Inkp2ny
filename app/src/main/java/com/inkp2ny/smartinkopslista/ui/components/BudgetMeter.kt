package com.inkp2ny.smartinkopslista.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.inkp2ny.smartinkopslista.ui.theme.Dimens
import java.util.Locale

/** Andel av budgeten som räknas som "nära gränsen" och triggar varningsfärg. */
private const val WARNING_THRESHOLD = 0.9f

@Composable
fun BudgetMeter(
    budget: Double,
    spent: Double,
    onEditBudget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val progress = if (budget <= 0.0) 1f else (spent / budget).toFloat().coerceIn(0f, 1f)
    val isOverBudget = budget > 0.0 && spent > budget
    val isNearLimit = budget > 0.0 && spent / budget >= WARNING_THRESHOLD
    val meterColor = if (isNearLimit) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(Dimens.SpacingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Budget & Pris",
                    style = MaterialTheme.typography.titleMedium,
                )
                TextButton(onClick = onEditBudget) {
                    Text("Ändra budget")
                }
            }

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .clip(RoundedCornerShape(percent = 50)),
                color = meterColor,
                trackColor = MaterialTheme.colorScheme.surface,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = String.format(
                        Locale.getDefault(),
                        "%.2f kr av %.2f kr",
                        spent,
                        budget,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (isNearLimit) {
                    Text(
                        text = if (isOverBudget) "Över budget!" else "Nära budgetgränsen!",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}
