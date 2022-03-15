package in_.turker.nasarovervehicle.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.data.model.Photo
import in_.turker.nasarovervehicle.databinding.DialogDetailBinding
import in_.turker.nasarovervehicle.databinding.DialogFilterBinding
import in_.turker.nasarovervehicle.utils.CameraType
import in_.turker.nasarovervehicle.utils.loadImagesWithGlide

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */

fun applyFilter(
    context: Activity,
    okAction: ((CameraType?) -> Unit)? = null
) {
    Dialog(context).apply {

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogFilterBinding.inflate(inflater)
        setContentView(binding.root)

        setCancelable(false)

        var cameraType: CameraType?

        binding.apply {

            imgClose.setOnClickListener {
                if (!context.isFinishing) {
                    dismiss()
                }
            }

            btnApply.setOnClickListener {

                cameraType = when (rgCamera.checkedRadioButtonId) {

                    R.id.rbFhaz -> {
                        CameraType.FHAZ
                    }
                    R.id.rbRhaz -> {
                        CameraType.RHAZ
                    }
                    R.id.rbMast -> {
                        CameraType.MAST
                    }
                    R.id.rbChemcam -> {
                        CameraType.CHEMCAM
                    }
                    R.id.rbMahli -> {
                        CameraType.MAHLI
                    }
                    R.id.rbMardi -> {
                        CameraType.MARDI
                    }
                    R.id.rbNavcam -> {
                        CameraType.NAVCAM
                    }
                    R.id.rbPancam -> {
                        CameraType.PANCAM
                    }
                    R.id.rbMinites -> {
                        CameraType.MINITES
                    }
                    else -> {
                        null
                    }

                }

                okAction?.invoke(cameraType)

                dismiss()
            }

        }

        if (!context.isFinishing) {
            show()
        }
    }
}


fun showDetail(
    context: Activity,
    vehicle: Photo
) {
    Dialog(context).apply {

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogDetailBinding.inflate(inflater)
        setContentView(binding.root)

        setCancelable(false)

        binding.apply {
            imgPhoto.loadImagesWithGlide(vehicle.imgSrc)
            txtTakePhotoDate.text = vehicle.earthDate
            txtVehicleName.text = vehicle.rover.name
            txtCameraName.text = vehicle.camera.name
            txtVehicleStatus.text = vehicle.rover.status
            txtLaunchDate.text = vehicle.rover.launchDate
            txtLandingDate.text = vehicle.rover.landingDate

            imgClose.setOnClickListener {
                if (!context.isFinishing) {
                    dismiss()
                }
            }
        }

        if (!context.isFinishing) {
            show()
        }
    }
}