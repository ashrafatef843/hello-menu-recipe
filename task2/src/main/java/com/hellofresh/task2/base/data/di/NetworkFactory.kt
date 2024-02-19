package com.hellofresh.task2.base.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hellofresh.task2.BuildConfig
import com.hellofresh.task2.recipes.data.api.RecipesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkFactory {

    private const val TIMEOUT = 30L
    private const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

    private fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()

    private fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private fun providesRetrofit(
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(providesOkHttp())
        addConverterFactory(
            GsonConverterFactory.create(provideGson())
        )
    }.build()

    fun providesRecipesApi(): RecipesApi =
        providesRetrofit().create(RecipesApi::class.java)
}
