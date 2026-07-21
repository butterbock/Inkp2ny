package com.inkp2ny.smartinkopslista.ui.viewmodel

import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem

data class ShoppingUiState(
    val items: List<ShoppingItem> = emptyList(),
    val isLoading: Boolean = true,
) {
    val pendingItems: List<ShoppingItem>
        get() = items.filter { !it.isCompleted }

    val completedItems: List<ShoppingItem>
        get() = items.filter { it.isCompleted }

    val totalEstimatedPrice: Double
        get() = pendingItems.sumOf { it.estimatedPrice }
}
