package in_.turker.nasarovervehicle.ui.fragment.spirit

import android.app.Application
import androidx.fragment.app.viewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.base.BaseViewModel
import in_.turker.nasarovervehicle.databinding.FragmentOpportunityBinding
import in_.turker.nasarovervehicle.databinding.FragmentSpiritBinding
import in_.turker.nasarovervehicle.ui.fragment.opportunity.OpportunityVM
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */
@HiltViewModel
class SpiritVM  @Inject constructor(
    myApp: Application
) : BaseViewModel(app = myApp)