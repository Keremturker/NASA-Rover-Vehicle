package in_.turker.nasarovervehicle.network

import com.google.gson.GsonBuilder
import in_.turker.nasarovervehicle.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */


const val CONNECTION_TIMEOUT_SEC = 5 * 60L

interface APIClient {
    val apiCollect: NasaService
}

@Singleton
class APIClientImpl @Inject constructor() : APIClient {

    override val apiCollect: NasaService by lazy {
        clientCollect.create(NasaService::class.java)
    }

    private val clientCollect: Retrofit by lazy {
        retrofitBuilderCollect.client(okHttpClientCollect).build()
    }

    private val retrofitBuilderCollect: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    private val okHttpClientCollect: OkHttpClient by lazy {
        okHttpClientBuilderCollect.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
        okHttpClientBuilderCollect.build()
    }

    private val okHttpClientBuilderCollect by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        builder
    }
}