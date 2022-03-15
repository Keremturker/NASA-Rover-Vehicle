package in_.turker.nasarovervehicle.ui.fragment.curiosity

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.nasarovervehicle.base.BaseViewModel
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.data.repository.NasaRepository
import in_.turker.nasarovervehicle.utils.CameraType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@HiltViewModel
class CuriosityVM @Inject constructor(
    myApp: Application,
    private val nasaRepository: NasaRepository

) : BaseViewModel(app = myApp) {

    fun getCuriosity(camera: CameraType? = null): Flow<PagingData<Photo>> {
        val repoItemsUiStates = nasaRepository.getCuriosity(camera)
            .map { pagingData ->
                pagingData.map { result -> result }
            }.cachedIn(viewModelScope)
        return repoItemsUiStates
    }

}