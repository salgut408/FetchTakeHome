package com.example.android.fetchtakehomeapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import com.example.android.fetchtakehomeapp.models.JsonResponse

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: JsonResponseModel): Long

    @Query("SELECT * FROM jsonresponsemodel ORDER BY listId ASC")
    suspend fun getInfoSortByListId(): List<JsonResponseModel>

    @Query("SELECT * FROM jsonresponsemodel WHERE name IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoSortByListIdExNulls(): List<JsonResponseModel>

    @Query("SELECT * FROM jsonresponsemodel WHERE NULLIF(name, '') IS NOT NULL ORDER BY listId ASC ")
    suspend fun getInfoSortByListIdExNullsExBlanks(): List<JsonResponseModel>

}