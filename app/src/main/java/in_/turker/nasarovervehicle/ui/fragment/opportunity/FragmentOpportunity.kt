package in_.turker.nasarovervehicle.ui.fragment.opportunity

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.FragmentOpportunityBinding
import in_.turker.nasarovervehicle.ui.dialog.applyFilter
import in_.turker.nasarovervehicle.ui.fragment.curiosity.VehiclePhotoAdapter
import in_.turker.nasarovervehicle.utils.collect
import in_.turker.nasarovervehicle.utils.collectLast
import in_.turker.nasarovervehicle.utils.visibleIf
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */
@AndroidEntryPoint
class FragmentOpportunity : BaseFragment<FragmentOpportunityBinding, OpportunityVM>() {

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
            action = ::setOpportunityUiState
        )

        binding.rvOpportunity.apply {
            adapter = vehiclePhotoAdapter
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    private fun prepareToolbar() {
        binding.toolbar.apply {
            txtTitle.text = getString(R.string.opportunity)

            imgFilter.setOnClickListener {
                applyFilter(requireActivity()) {
                    collectLast(viewModel.getOpportunity(it), ::setVehiclePhoto)
                }
            }
        }
    }

    private fun setOpportunityUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.txtError.visibleIf(false)
                binding.progressBar.visibleIf(true)
            }

            is LoadState.NotLoading -> {
                binding.progressBar.visibleIf(false)
                val hasItem = vehiclePhotoAdapter.itemCount != 0
                binding.rvOpportunity.visibleIf(hasItem)
                binding.txtError.visibleIf(!hasItem)
                binding.txtError.text = getString(R.string.no_record)

            }

            is LoadState.Error -> {
                binding.rvOpportunity.visibleIf(false)
                binding.progressBar.visibleIf(false)
                binding.txtError.visibleIf(true)
                binding.txtError.text =
                    loadState.error.localizedMessage ?: getString(R.string.something_went_wrong)
            }
        }
    }

    private suspend fun setVehiclePhoto(itemsPagingData: PagingData<Photo>) {
        vehiclePhotoAdapter.submitData(itemsPagingData)
    }

    private fun onClickAction(item: Photo) {}

}