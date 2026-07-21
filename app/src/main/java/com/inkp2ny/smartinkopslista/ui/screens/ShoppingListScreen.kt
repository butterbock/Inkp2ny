package com.inkp2ny.smartinkopslista.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository
import com.inkp2ny.smartinkopslista.ui.components.AddItemDialog
import com.inkp2ny.smartinkopslista.ui.components.ShoppingItemCard
import com.inkp2ny.smartinkopslista.ui.theme.Dimens
import com.inkp2ny.smartinkopslista.ui.viewmodel.ShoppingViewModel
import com.inkp2ny.smartinkopslista.ui.viewmodel.ShoppingViewModelFactory
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    repository: ShoppingRepository,
    viewModel: ShoppingViewModel = viewModel(factory = ShoppingViewModelFactory(repository)),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Smart Inköpslista",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Lägg till vara")
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(
                start = Dimens.SpacingMedium,
                end = Dimens.SpacingMedium,
                top = Dimens.SpacingMedium,
                bottom = Dimens.SpacingExtraLarge,
            ),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall),
        ) {
            item {
                Text(
                    text = "Att handla • ${
                        String.format(Locale.getDefault(), "%.2f kr", uiState.totalEstimatedPrice)
                    }",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = Dimens.SpacingSmall),
                )
            }

            if (uiState.pendingItems.isEmpty() && !uiState.isLoading) {
                item { EmptyState() }
            }

            items(uiState.pendingItems, key = { it.id }) { item ->
                ShoppingItemCard(
                    item = item,
                    onToggleCompleted = viewModel::toggleCompleted,
                    onDelete = viewModel::deleteItem,
                )
            }

            if (uiState.completedItems.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimens.SpacingLarge, bottom = Dimens.SpacingSmall),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Klart (${uiState.completedItems.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        TextButton(onClick = viewModel::clearCompletedItems) {
                            Text("Rensa klara")
                        }
                    }
                }

                items(uiState.completedItems, key = { it.id }) { item ->
                    ShoppingItemCard(
                        item = item,
                        onToggleCompleted = viewModel::toggleCompleted,
                        onDelete = viewModel::deleteItem,
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddItemDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, quantity, category, price ->
                viewModel.addItem(name, quantity, category, price)
                showAddDialog = false
            },
        )
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.SpacingExtraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall),
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Din lista är tom",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "Tryck på + för att lägga till en vara",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
