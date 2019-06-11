package com.xoxoton.svaped.ui.common

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.xoxoton.svaped.R

class PopupDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var myView = inflater!!.inflate(R.layout.popup_bike, container, false)
        var imeiView = myView.findViewById<TextView>(R.id.imei_text)
        var numberView = myView.findViewById<TextView>(R.id.number_text)
        return myView
    }
}