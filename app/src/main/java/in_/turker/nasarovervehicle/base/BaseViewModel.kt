package in_.turker.nasarovervehicle.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 14.03.2022.
 */

@HiltViewModel
open class BaseViewModel
@Inject constructor(
    app: Application
) : AndroidViewModel(app)