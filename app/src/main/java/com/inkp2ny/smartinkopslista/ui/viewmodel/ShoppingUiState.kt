package com.inkp2ny.smartinkopslista.ui.viewmodel

import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem

data class ShoppingUiState(
    val items: List<ShoppingItem> = emptyList(),
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val budget: Double = 1000.0,
) {
    private val filteredItems: List<ShoppingItem>
        get() = if (searchQuery.isBlank()) {
            items
        } else {
            items.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }

    val pendingItems: List<ShoppingItem>
        get() = filteredItems.filter { !it.isCompleted }

    val completedItems: List<ShoppingItem>
        get() = filteredItems.filter { it.isCompleted }

    /** Summan av alla aktiva (ej avbockade) varor, oavsett sökfilter — underlag för budgetmätaren. */
    val totalEstimatedPrice: Double
        get() = items.filter { !it.isCompleted }.sumOf { it.estimatedPrice }
}
