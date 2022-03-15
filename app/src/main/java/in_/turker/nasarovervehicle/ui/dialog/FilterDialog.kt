package in_.turker.nasarovervehicle.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.RadioGroup
import in_.turker.nasarovervehicle.R
import in_.turker.nasarovervehicle.utils.CameraType

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */

fun applyFilter(
    context: Activity,
    okAction: ((CameraType?) -> Unit)? = null
) {
    Dialog(context).apply {

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_filter)
        setCancelable(false)

        var cameraType: CameraType?

        val btnApply = findViewById<Button>(R.id.btnApply)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val rgCamera = findViewById<RadioGroup>(R.id.rgCamera)

        btnCancel.setOnClickListener {
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


        if (!context.isFinishing) {
            show()
        }
    }


}