package in_.turker.nasarovervehicle.data.paging_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.network.NasaService
import in_.turker.nasarovervehicle.utils.CameraType

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */
class SpiritPagingDataSource(
    private val nasaService: NasaService,
    private val camera: CameraType? = null

) :
    PagingSource<Int, Photo>() {
    private val STARTING_PAGE_INDEX = 1


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                nasaService.getSpirit(page = page, camera = camera?.name)
            LoadResult.Page(
                data = response.photos,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.photos.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
