package com.inkp2ny.smartinkopslista.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inkp2ny.smartinkopslista.data.local.BudgetPreferences
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository

class ShoppingViewModelFactory(
    private val repository: ShoppingRepository,
    private val budgetPreferences: BudgetPreferences,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(ShoppingViewModel::class.java)) {
            "Unknown ViewModel class: $modelClass"
        }
        return ShoppingViewModel(repository, budgetPreferences) as T
    }
}
