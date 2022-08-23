package com.example.android.fetchtakehomeapp.repository

import android.content.Context
import com.example.android.fetchtakehomeapp.api.FetchApi
import com.example.android.fetchtakehomeapp.db.ItemDatabase
import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import com.example.android.fetchtakehomeapp.models.JsonResponseDtoMapper
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

}