package in_.turker.nasarovervehicle.ui.fragment.curiosity

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.base.BaseFragment
import in_.turker.nasarovervehicle.databinding.FragmentCuriosityBinding

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@AndroidEntryPoint
class FragmentCuriosity : BaseFragment<FragmentCuriosityBinding, CuriosityVM>() {

    override val viewModel: CuriosityVM by viewModels()

    override fun getViewBinding() = FragmentCuriosityBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {}

}