package com.example.android.fetchtakehomeapp.di

import android.content.Context
import androidx.room.Room
import com.example.android.fetchtakehomeapp.api.FetchApi
import com.example.android.fetchtakehomeapp.db.ItemDao
import com.example.android.fetchtakehomeapp.db.ItemDatabase
import com.example.android.fetchtakehomeapp.models.JsonResponseDtoMapper
import com.example.android.fetchtakehomeapp.repository.ItemRepository
import com.example.android.fetchtakehomeapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideItemDao(itemDatabase: ItemDatabase): ItemDao = itemDatabase.getDao()

    @Provides
    fun provideMapper(): JsonResponseDtoMapper = JsonResponseDtoMapper()

    @Provides
    @Singleton
    fun provideItemDatabase(@ApplicationContext context: Context): ItemDatabase =
        Room.databaseBuilder(
            context,
            ItemDatabase:: class.java,
            "database"
        ).build()

    @Provides
    fun provideItemRepository(
        jsonResponseDtoMapper: JsonResponseDtoMapper,
        itemDatabase: ItemDatabase,
        api: FetchApi,
        @ApplicationContext context: Context

    ): ItemRepository = ItemRepository(
        jsonResponseDtoMapper,
        itemDatabase,
        api,
        context
    )

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Singleton
    @Provides
    fun provideApi(okhttpClient: OkHttpClient): FetchApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApi::class.java)
}