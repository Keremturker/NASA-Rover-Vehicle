package in_.turker.nasarovervehicle.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import in_.turker.nasarovervehicle.data.paging_data_source.CuriosityPagingDataSource
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.network.APIClientImpl
import in_.turker.nasarovervehicle.utils.CameraType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */
class NasaRepository @Inject constructor(private val apiServiceImpl: APIClientImpl) {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    fun getCuriosity(camera: CameraType? = null): Flow<PagingData<Photo>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE), pagingSourceFactory = {
            CuriosityPagingDataSource(apiServiceImpl.apiCollect, camera)
        }).flow
    }
}