package com.inkp2ny.smartinkopslista.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.inkp2ny.smartinkopslista.data.local.BudgetPreferences
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository
import com.inkp2ny.smartinkopslista.ui.navigation.AppTab

@Composable
fun MainScreen(
    repository: ShoppingRepository,
    budgetPreferences: BudgetPreferences,
) {
    var selectedTab by remember { mutableStateOf(AppTab.ShoppingList) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${selectedTab.emoji} ${selectedTab.label}",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
        bottomBar = {
            NavigationBar {
                AppTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Text(tab.emoji) },
                        label = { Text(tab.label) },
                    )
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (selectedTab) {
                AppTab.ShoppingList -> ShoppingListTab(
                    repository = repository,
                    budgetPreferences = budgetPreferences,
                )
                AppTab.Recipe -> RecipeTab()
                AppTab.Stores -> StoresTab()
                AppTab.ShareSync -> ShareSyncTab()
            }
        }
    }
}
