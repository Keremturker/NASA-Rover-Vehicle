package in_.turker.nasarovervehicle.ui.fragment.spirit

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.databinding.FragmentSpiritBinding
import in_.turker.nasarovervehicle.ui.fragment.opportunity.OpportunityVM

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@AndroidEntryPoint
class FragmentSpirit : BaseFragment<FragmentSpiritBinding, SpiritVM>() {

    override val viewModel: SpiritVM by viewModels()

    override fun getViewBinding() = FragmentSpiritBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {}

}