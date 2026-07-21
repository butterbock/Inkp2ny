package com.inkp2ny.smartinkopslista.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inkp2ny.smartinkopslista.data.local.BudgetPreferences
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository
import com.inkp2ny.smartinkopslista.ui.components.AddItemDialog
import com.inkp2ny.smartinkopslista.ui.components.BudgetEditDialog
import com.inkp2ny.smartinkopslista.ui.components.BudgetMeter
import com.inkp2ny.smartinkopslista.ui.components.ShoppingItemCard
import com.inkp2ny.smartinkopslista.ui.components.ShoppingSearchField
import com.inkp2ny.smartinkopslista.ui.components.SwipeToDeleteItem
import com.inkp2ny.smartinkopslista.ui.theme.Dimens
import com.inkp2ny.smartinkopslista.ui.viewmodel.ShoppingViewModel
import com.inkp2ny.smartinkopslista.ui.viewmodel.ShoppingViewModelFactory

@Composable
fun ShoppingListTab(
    repository: ShoppingRepository,
    budgetPreferences: BudgetPreferences,
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = viewModel(
        factory = ShoppingViewModelFactory(repository, budgetPreferences),
    ),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showBudgetDialog by remember { mutableStateOf(false) }
    var isCompletedExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            BudgetMeter(
                budget = uiState.budget,
                spent = uiState.totalEstimatedPrice,
                onEditBudget = { showBudgetDialog = true },
                modifier = Modifier.padding(
                    horizontal = Dimens.SpacingMedium,
                    vertical = Dimens.SpacingSmall,
                ),
            )
            ShoppingSearchField(
                query = uiState.searchQuery,
                onQueryChange = viewModel::updateSearchQuery,
                modifier = Modifier.padding(horizontal = Dimens.SpacingMedium),
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = Dimens.SpacingMedium,
                    end = Dimens.SpacingMedium,
                    top = Dimens.SpacingMedium,
                    bottom = Dimens.FabClearance,
                ),
                verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall),
            ) {
                if (uiState.pendingItems.isEmpty() && !uiState.isLoading) {
                    item { EmptyState() }
                }

                items(uiState.pendingItems, key = { it.id }) { item ->
                    SwipeToDeleteItem(onDelete = { viewModel.deleteItem(item) }) {
                        ShoppingItemCard(
                            item = item,
                            onToggleCompleted = viewModel::toggleCompleted,
                        )
                    }
                }

                if (uiState.completedItems.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.SpacingLarge, bottom = Dimens.SpacingSmall)
                                .clickable { isCompletedExpanded = !isCompletedExpanded },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Avbockade varor (${uiState.completedItems.size})",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Icon(
                                imageVector = if (isCompletedExpanded) {
                                    Icons.Filled.KeyboardArrowUp
                                } else {
                                    Icons.Filled.KeyboardArrowDown
                                },
                                contentDescription = if (isCompletedExpanded) "Dölj avbockade varor" else "Visa avbockade varor",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }

                    if (isCompletedExpanded) {
                        items(uiState.completedItems, key = { it.id }) { item ->
                            SwipeToDeleteItem(onDelete = { viewModel.deleteItem(item) }) {
                                ShoppingItemCard(
                                    item = item,
                                    onToggleCompleted = viewModel::toggleCompleted,
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimens.SpacingLarge),
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Lägg till vara")
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

    if (showBudgetDialog) {
        BudgetEditDialog(
            currentBudget = uiState.budget,
            onDismiss = { showBudgetDialog = false },
            onConfirm = { newBudget ->
                viewModel.updateBudget(newBudget)
                showBudgetDialog = false
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
