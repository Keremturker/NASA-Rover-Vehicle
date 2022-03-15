package in_.turker.nasarovervehicle.ui.fragment.opportunity

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.FragmentOpportunityBinding
import in_.turker.nasarovervehicle.ui.fragment.curiosity.VehiclePhotoAdapter
import in_.turker.nasarovervehicle.utils.collect
import in_.turker.nasarovervehicle.utils.collectLast
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */
@AndroidEntryPoint
class FragmentOpportunity: BaseFragment<FragmentOpportunityBinding, OpportunityVM>() {

    override val viewModel: OpportunityVM by viewModels()
    private var vehiclePhotoAdapter = VehiclePhotoAdapter(::onClickAction)
    override fun getViewBinding() = FragmentOpportunityBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {
        prepareRecyclerView()
        prepareToolbar()
        collectLast(viewModel.getOpportunity(), ::setVehiclePhoto)
    }

    private fun prepareRecyclerView() {
        collect(flow = vehiclePhotoAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setCuriosityUiState
        )

        binding.rvOpportunity.apply {
            adapter = vehiclePhotoAdapter
            layoutManager = GridLayoutManager(
                requireContext(),2
            )
        }
    }

    private fun prepareToolbar() {
        binding.toolbar.apply {
            txtTitle.text=getString(R.string.opportunity)

            imgFilter.setOnClickListener {
                Log.d("test123","opportunity")
            }
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