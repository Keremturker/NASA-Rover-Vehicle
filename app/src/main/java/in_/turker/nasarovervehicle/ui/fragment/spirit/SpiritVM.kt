package in_.turker.nasarovervehicle.ui.fragment.spirit

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.base.BaseViewModel
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.data.repository.NasaRepository
import in_.turker.nasarovervehicle.databinding.FragmentOpportunityBinding
import in_.turker.nasarovervehicle.databinding.FragmentSpiritBinding
import in_.turker.nasarovervehicle.ui.fragment.opportunity.OpportunityVM
import in_.turker.nasarovervehicle.utils.CameraType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */
@HiltViewModel
class SpiritVM  @Inject constructor(
    myApp: Application,
    private val nasaRepository: NasaRepository
) : BaseViewModel(app = myApp){

    fun getSpirit(camera: CameraType? = null): Flow<PagingData<Photo>> {
        val repoItemsUiStates = nasaRepository.getSpirit(camera)
            .map { pagingData ->
                pagingData.map { result -> result }
            }.cachedIn(viewModelScope)
        return repoItemsUiStates
    }
}