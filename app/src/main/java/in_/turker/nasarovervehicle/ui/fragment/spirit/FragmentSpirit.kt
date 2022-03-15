package in_.turker.nasarovervehicle.ui.fragment.spirit

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.FragmentSpiritBinding
import in_.turker.nasarovervehicle.ui.dialog.applyFilter
import in_.turker.nasarovervehicle.ui.fragment.curiosity.VehiclePhotoAdapter
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
        prepareToolbar()
        collectLast(viewModel.getSpirit(), ::setVehiclePhoto)

    }

    private fun prepareRecyclerView() {
        collect(flow = vehiclePhotoAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setSpiritUiState
        )

        binding.rvSpirit.apply {
            adapter = vehiclePhotoAdapter
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    private fun prepareToolbar() {
        binding.toolbar.apply {
            txtTitle.text = getString(R.string.spirit)

            imgFilter.setOnClickListener {
                applyFilter(requireActivity()) {
                    collectLast(viewModel.getSpirit(it), ::setVehiclePhoto)
                }
            }
        }
    }

    private fun setSpiritUiState(loadState: LoadState) {
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