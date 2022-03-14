package in_.turker.nasarovervehicle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.base.BaseActivity
import in_.turker.nasarovervehicle.databinding.ActivitySingleBinding
import in_.turker.nasarovervehicle.ui.SingleVM

@AndroidEntryPoint
class SingleActivity : BaseActivity<ActivitySingleBinding, SingleVM>(){

    override val viewModel: SingleVM by viewModels()

    override fun getViewBinding() = ActivitySingleBinding.inflate(layoutInflater)

    override fun onActivityCreated() {}

    override fun observe() {}

}