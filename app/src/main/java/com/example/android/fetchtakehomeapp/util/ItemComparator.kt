package com.example.android.fetchtakehomeapp.util

import com.example.android.fetchtakehomeapp.domain.JsonResponseModel
import kotlin.Comparator

class ItemComparator {
    companion object: Comparator<JsonResponseModel> {
        override fun compare(a: JsonResponseModel?, b: JsonResponseModel?): Int = when {
            a?.listId != b?.listId -> a?.listId!! - b?.listId!!
            a?.id != b?.id -> a?.id!! - b?.id!!
            else -> 0
        }
    }
}