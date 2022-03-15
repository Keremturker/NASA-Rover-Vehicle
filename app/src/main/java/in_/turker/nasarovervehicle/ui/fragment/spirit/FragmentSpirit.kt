package in_.turker.nasarovervehicle.ui.fragment.spirit

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.FragmentSpiritBinding
import in_.turker.nasarovervehicle.ui.fragment.curiosity.VehiclePhotoAdapter
import in_.turker.nasarovervehicle.ui.fragment.opportunity.OpportunityVM
import in_.turker.nasarovervehicle.utils.collect
import in_.turker.nasarovervehicle.utils.collectLast
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@AndroidEntryPoint
class FragmentSpirit : BaseFragment<FragmentSpiritBinding, SpiritVM>() {

    override val viewModel: SpiritVM by viewModels()
    private var vehiclePhotoAdapter = VehiclePhotoAdapter(::onClickAction)

    override fun getViewBinding() = FragmentSpiritBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {
        prepareRecyclerView()
        collectLast(viewModel.getSpirit(), ::setVehiclePhoto)

    }

    private fun prepareRecyclerView() {
        collect(flow = vehiclePhotoAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setCuriosityUiState
        )

        binding.rvSpirit.apply {
            adapter = vehiclePhotoAdapter
            layoutManager = GridLayoutManager(
                requireContext(),2
            )
        }
    }

    private fun setCuriosityUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {}

            is LoadState.NotLoading -> {}

            is LoadState.Error -> {}
        }
    }

    private suspend fun setVehiclePhoto(itemsPagingData: PagingData<Photo>) {
        vehiclePhotoAdapter.submitData(itemsPagingData)
    }

    private fun onClickAction(item: Photo) {}


}