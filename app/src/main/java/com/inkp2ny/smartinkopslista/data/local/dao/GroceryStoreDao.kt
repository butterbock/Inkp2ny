package com.inkp2ny.smartinkopslista.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.inkp2ny.smartinkopslista.data.local.entity.GroceryStore
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryStoreDao {

    @Query("SELECT * FROM grocery_stores ORDER BY name ASC")
    fun getAllStores(): Flow<List<GroceryStore>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store: GroceryStore): Long

    @Update
    suspend fun updateStore(store: GroceryStore)

    @Delete
    suspend fun deleteStore(store: GroceryStore)
}
