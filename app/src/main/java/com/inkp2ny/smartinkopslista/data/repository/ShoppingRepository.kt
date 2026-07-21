package com.inkp2ny.smartinkopslista.data.repository

import com.inkp2ny.smartinkopslista.data.local.dao.ShoppingDao
import com.inkp2ny.smartinkopslista.data.local.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

class ShoppingRepository(private val shoppingDao: ShoppingDao) {

    val allItems: Flow<List<ShoppingItem>> = shoppingDao.getAllItems()

    fun getItemById(id: Int): Flow<ShoppingItem?> = shoppingDao.getItemById(id)

    suspend fun addItem(item: ShoppingItem) = shoppingDao.insertItem(item)

    suspend fun updateItem(item: ShoppingItem) = shoppingDao.updateItem(item)

    suspend fun deleteItem(item: ShoppingItem) = shoppingDao.deleteItem(item)

    suspend fun setCompleted(id: Int, isCompleted: Boolean) =
        shoppingDao.setCompleted(id, isCompleted)

    suspend fun deleteCompletedItems() = shoppingDao.deleteCompletedItems()

    suspend fun deleteAllItems() = shoppingDao.deleteAllItems()
}
