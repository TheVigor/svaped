package com.xoxoton.svaped.ui.features.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.model.ParkingPointDO
import com.xoxoton.svaped.ui.extensions.loadImage
import kotlinx.android.synthetic.main.parking_bottom_sheet_layout.*

class ParkingBottomSheetFragment: BottomSheetDialogFragment() {

    companion object {
        const val PARKING_TAG = "PARKING_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.parking_bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<ParkingPointDO>(PARKING_TAG)?.let { initViews(it) }
    }


    private fun initViews(parkingPoint: ParkingPointDO) {
        tv_parking_name.text = parkingPoint.name
        tv_parking_note.text = parkingPoint.note
        tv_parking_city_id.text = parkingPoint.getCityById()
        tv_parking_lat.text = parkingPoint.latitude.toString()
        tv_parking_lng.text = parkingPoint.longitude.toString()

        iv_parking_photo.loadImage(parkingPoint.image)
    }
}