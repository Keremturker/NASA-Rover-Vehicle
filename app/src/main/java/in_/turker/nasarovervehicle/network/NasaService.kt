package in_.turker.nasarovervehicle.network

import in_.turker.nasarovervehicle.BuildConfig
import in_.turker.nasarovervehicle.data.model.NasaResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */
interface NasaService {

    @GET("curiosity/photos?")
    suspend fun getCuriosity(
        @Query("sol") sol: Int = 1000,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("camera") camera: String? = null
    ): NasaResponse
}