package in_.turker.nasarovervehicle.ui.activity


import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.base.BaseActivity
import in_.turker.nasarovervehicle.databinding.ActivitySingleBinding


@AndroidEntryPoint
class SingleActivity : BaseActivity<ActivitySingleBinding, SingleVM>() {

    override val viewModel: SingleVM by viewModels()

    override fun getViewBinding() = ActivitySingleBinding.inflate(layoutInflater)


    private lateinit var navController: NavController

    override fun onActivityCreated() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = (navHostFragment as NavHostFragment).navController
        binding.navView.setupWithNavController(navController)
    }

    override fun observe() {}
}