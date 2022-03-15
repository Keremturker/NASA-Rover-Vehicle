package in_.turker.nasarovervehicle.data.model


import com.google.gson.annotations.SerializedName

data class NasaResponse(
    @SerializedName("photos")
    val photos: List<Photo>
)