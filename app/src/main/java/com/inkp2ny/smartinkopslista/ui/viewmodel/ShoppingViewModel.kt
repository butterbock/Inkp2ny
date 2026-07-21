package com.inkp2ny.smartinkopslista.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingCategories
import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ShoppingUiState())
    val uiState: StateFlow<ShoppingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allItems.collect { items ->
                _uiState.update { it.copy(items = items, isLoading = false) }
            }
        }
    }

    fun addItem(
        name: String,
        quantity: String,
        category: String = ShoppingCategories.GENERAL,
        estimatedPrice: Double = 0.0,
    ) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.addItem(
                ShoppingItem(
                    name = name.trim(),
                    quantity = quantity.trim(),
                    category = category,
                    estimatedPrice = estimatedPrice,
                ),
            )
        }
    }

    fun updateItem(item: ShoppingItem) {
        viewModelScope.launch { repository.updateItem(item) }
    }

    fun toggleCompleted(item: ShoppingItem) {
        viewModelScope.launch { repository.setCompleted(item.id, !item.isCompleted) }
    }

    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch { repository.deleteItem(item) }
    }

    fun clearCompletedItems() {
        viewModelScope.launch { repository.deleteCompletedItems() }
    }
}
