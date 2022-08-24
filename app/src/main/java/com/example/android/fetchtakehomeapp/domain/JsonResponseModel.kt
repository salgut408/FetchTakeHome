package com.example.android.fetchtakehomeapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class JsonResponseModel(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var listId: Int? = null,
    var name: String? = null,
)