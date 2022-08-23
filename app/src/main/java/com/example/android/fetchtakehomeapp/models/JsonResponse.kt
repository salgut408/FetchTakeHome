package com.example.android.fetchtakehomeapp.models

import com.google.gson.annotations.SerializedName

data class JsonResponse(
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("listId" ) var listId : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null

)
