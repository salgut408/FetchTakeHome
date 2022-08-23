package com.example.android.fetchtakehomeapp.network

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}