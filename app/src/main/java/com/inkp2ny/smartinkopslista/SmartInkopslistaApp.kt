package com.inkp2ny.smartinkopslista

import android.app.Application
import androidx.room.Room
import com.inkp2ny.smartinkopslista.data.local.AppDatabase
import com.inkp2ny.smartinkopslista.data.local.BudgetPreferences
import com.inkp2ny.smartinkopslista.data.repository.ShoppingRepository

class SmartInkopslistaApp : Application() {

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    val shoppingRepository: ShoppingRepository by lazy {
        ShoppingRepository(database.shoppingDao())
    }

    val budgetPreferences: BudgetPreferences by lazy {
        BudgetPreferences(this)
    }
}
