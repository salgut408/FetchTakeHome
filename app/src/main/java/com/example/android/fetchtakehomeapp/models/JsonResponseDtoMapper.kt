package com.example.android.fetchtakehomeapp.models

import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import com.example.android.fetchtakehomeapp.network.DomainMapper

class JsonResponseDtoMapper: DomainMapper<JsonResponse, JsonResponseModel> {
    override fun mapToDomainModel(model: JsonResponse): JsonResponseModel {
        return JsonResponseModel(
            id = model.id,
            listId = model.listId,
            name = model.name
        )
    }

    fun toDomainList(initial: List<JsonResponse>): List<JsonResponseModel>{
        return initial.map { mapToDomainModel(it) }
    }
}