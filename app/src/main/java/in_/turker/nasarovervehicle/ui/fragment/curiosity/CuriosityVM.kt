package in_.turker.nasarovervehicle.ui.fragment.curiosity

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.nasarovervehicle.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@HiltViewModel
class CuriosityVM @Inject constructor(
    myApp: Application
) : BaseViewModel(app = myApp)