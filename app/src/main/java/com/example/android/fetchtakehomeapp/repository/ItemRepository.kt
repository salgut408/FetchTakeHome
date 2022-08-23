package com.example.android.fetchtakehomeapp.repository

import android.content.Context
import android.util.Log
import com.example.android.fetchtakehomeapp.api.FetchApi
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
    val itemDatabase: ItemDatabase,
    val api: FetchApi,
    val context: Context,
) {
    suspend fun getListOfItems(): List<JsonResponseModel> {
        val result = api.getFetchInformation().body()!!
        return jsonResponseDtoMapper.toDomainList(result)
    }

suspend fun getSortedList(): List<JsonResponseModel> {
   val result=  itemDatabase.getDao().getInfoSortByListId()
    return result

}

    suspend fun getSortedListExNulls(): List<JsonResponseModel> {
        val result = itemDatabase.getDao().getInfoSortByListIdExNulls()
        return result
    }
    suspend fun getSortedListExNullsExBlanks(): List<JsonResponseModel> {
        val result = itemDatabase.getDao().getInfoSortByListIdExNullsExBlanks()
        return result
    }

//    val items: Flow<List<JsonResponseModel>> = flow {
//        while (true) {
//            var items = api.getFetchInformation().body()!!
//            emit(jsonResponseDtoMapper.toDomainList(items))
//        }
//    }

    val items: Flow<List<JsonResponseModel>> = flow {
        while (true) {
            var items = itemDatabase.getDao().getInfoSortByListIdExNullsExBlanks()
            emit(items)
        }
    }

    suspend fun getInfoForDatabase() {
        withContext(Dispatchers.IO) {
            try {
                val items = api.getFetchInformation().body()!!
                val items2 = jsonResponseDtoMapper.toDomainList(items)
                for (i in items2){

                    itemDatabase.getDao().update(i)

                }
            } catch (err :Exception) {
                Log.i("Tag", "Failed")
            }
        }
    }

}