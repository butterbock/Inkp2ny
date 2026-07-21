package com.inkp2ny.smartinkopslista.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Kategorier som en [ShoppingItem] kan tillhöra. */
object ShoppingCategories {
    const val DAIRY = "Mejeri"
    const val PRODUCE = "Frukt & Grönt"
    const val MEAT = "Kött"
    const val PANTRY = "Skafferi"
    const val BREAD = "Bröd"
    const val BEVERAGE = "Dryck"
    const val GENERAL = "Allmänt"

    val ALL = listOf(DAIRY, PRODUCE, MEAT, PANTRY, BREAD, BEVERAGE, GENERAL)
}

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: String,
    val category: String = ShoppingCategories.GENERAL,
    val isCompleted: Boolean = false,
    val estimatedPrice: Double = 0.0,
)
