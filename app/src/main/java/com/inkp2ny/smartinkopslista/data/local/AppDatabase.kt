package com.inkp2ny.smartinkopslista.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inkp2ny.smartinkopslista.data.local.dao.GroceryStoreDao
import com.inkp2ny.smartinkopslista.data.local.dao.ShoppingDao
import com.inkp2ny.smartinkopslista.data.local.entity.GroceryStore
import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem

@Database(
    entities = [ShoppingItem::class, GroceryStore::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
    abstract fun groceryStoreDao(): GroceryStoreDao

    companion object {
        const val DATABASE_NAME = "smart_inkopslista.db"
    }
}
