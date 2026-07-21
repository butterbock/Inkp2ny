package com.inkp2ny.smartinkopslista.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM shopping_items ORDER BY isCompleted ASC, category ASC, name ASC")
    fun getAllItems(): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items WHERE id = :id")
    fun getItemById(id: Int): Flow<ShoppingItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem): Long

    @Update
    suspend fun updateItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("UPDATE shopping_items SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun setCompleted(id: Int, isCompleted: Boolean)

    @Query("DELETE FROM shopping_items WHERE isCompleted = 1")
    suspend fun deleteCompletedItems()

    @Query("DELETE FROM shopping_items")
    suspend fun deleteAllItems()
}
