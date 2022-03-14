package in_.turker.nasarovervehicle.ui.fragment.opportunity

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.databinding.FragmentCuriosityBinding
import in_.turker.nasarovervehicle.databinding.FragmentOpportunityBinding
import in_.turker.nasarovervehicle.ui.fragment.curiosity.CuriosityVM

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */
@AndroidEntryPoint
class FragmentOpportunity: BaseFragment<FragmentOpportunityBinding, OpportunityVM>() {

    override val viewModel: OpportunityVM by viewModels()

    override fun getViewBinding() = FragmentOpportunityBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {}

}