package com.inkp2ny.smartinkopslista.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_stores")
data class GroceryStore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isActive: Boolean = true,
    val radiusMeters: Double = 100.0,
)
