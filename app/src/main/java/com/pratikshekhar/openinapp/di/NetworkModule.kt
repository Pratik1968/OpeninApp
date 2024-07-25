package com.pratikshekhar.openinapp.di

import com.pratikshekhar.openinapp.Network.OAuthInterceptor
import com.pratikshekhar.openinapp.api.DataApi
import com.pratikshekhar.openinapp.database.AuthorizationToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.inopenapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun DataApi(retrofit: Retrofit): DataApi {

        return retrofit.create(DataApi::class.java)
    }
}