package in_.turker.nasarovervehicle.ui.fragment.curiosity

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.FragmentCuriosityBinding
import in_.turker.nasarovervehicle.ui.dialog.applyFilter
import in_.turker.nasarovervehicle.ui.dialog.showDetail
import in_.turker.nasarovervehicle.utils.collect
import in_.turker.nasarovervehicle.utils.collectLast
import in_.turker.nasarovervehicle.utils.visibleIf
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@AndroidEntryPoint
class FragmentCuriosity : BaseFragment<FragmentCuriosityBinding, CuriosityVM>() {

    override val viewModel: CuriosityVM by viewModels()
    private var vehiclePhotoAdapter = VehiclePhotoAdapter(::onClickAction)
    override fun getViewBinding() = FragmentCuriosityBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {
        prepareRecyclerView()
        prepareToolbar()

        collectLast(viewModel.getCuriosity(), ::setVehiclePhoto)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressBar.visibleIf(true)

            collectLast(viewModel.getCuriosity(), ::setVehiclePhoto)
        }
    }

    private fun prepareRecyclerView() {
        collect(flow = vehiclePhotoAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setCuriosityUiState
        )

        binding.rvCuriosity.apply {
            adapter = vehiclePhotoAdapter
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    private fun prepareToolbar() {
        binding.toolbar.apply {
            txtTitle.text = getString(R.string.curiosity)

            imgFilter.setOnClickListener {
                applyFilter(requireActivity()) {
                    collectLast(viewModel.getCuriosity(it), ::setVehiclePhoto)
                }
            }
        }
    }

    private fun setCuriosityUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.txtError.visibleIf(false)
                binding.progressBar.visibleIf(true)
            }

            is LoadState.NotLoading -> {
                binding.progressBar.visibleIf(false)
                val hasItem = vehiclePhotoAdapter.itemCount != 0
                binding.rvCuriosity.visibleIf(hasItem)
                binding.txtError.visibleIf(!hasItem)
                binding.txtError.text = getString(R.string.no_record)

            }

            is LoadState.Error -> {
                binding.rvCuriosity.visibleIf(false)
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

    private fun onClickAction(item: Photo) {
        showDetail(requireActivity(), item)
    }

}