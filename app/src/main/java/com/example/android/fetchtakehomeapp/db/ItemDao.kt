package com.example.android.fetchtakehomeapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: JsonResponseModel): Long
}