package com.example.android.fetchtakehomeapp.repository

import android.content.Context
import android.util.Log
import com.example.android.fetchtakehomeapp.api.FetchApi
import com.example.android.fetchtakehomeapp.db.ItemDao
import com.example.android.fetchtakehomeapp.db.ItemDatabase
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import com.example.android.fetchtakehomeapp.models.JsonResponse
import com.example.android.fetchtakehomeapp.models.JsonResponseDtoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepository @Inject constructor(
    val jsonResponseDtoMapper: JsonResponseDtoMapper,
    val dao: ItemDao,
    val api: FetchApi,
) {

    suspend fun getSortedListExNullsExBlanks(): List<JsonResponseModel> {
        val result = dao.getInfoSortByListIdExNullsExBlanks()
        return result
    }

    val items: Flow<List<JsonResponseModel>> = flow {
        while (true) {
            val items = dao.getInfoSortByListIdExNullsExBlanks()
            emit(items)
        }
    }

    suspend fun getInfoForDatabase() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()!!
                val items2 = jsonResponseDtoMapper.toDomainList(items)
                for (i in items2) {
                    dao.update(i)
                }
            } catch (err: Exception) {
                Log.i("Tag", "Failed")
            }
        }
    }
}