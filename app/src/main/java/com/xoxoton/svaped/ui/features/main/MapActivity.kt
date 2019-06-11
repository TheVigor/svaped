package com.xoxoton.svaped.ui.features.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.data.model.ParkingPointDO

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xoxoton.svaped.data.remote.SvapedClient
import com.xoxoton.svaped.ui.common.PopupDialog

import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.map.*
import kotlinx.android.synthetic.main.popup_bike.*

class MapActivity : AppCompatActivity() {
    private val MAPKIT_API_KEY = "f57d302b-98fd-45d5-94c4-4ef2110f517b"
    private val TARGET_LOCATION = Point(47.23135, 39.72328)

    private var mapView: MapView? = null
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        setContentView(R.layout.map)
        super.onCreate(savedInstanceState)
        mapView = findViewById(R.id.mapview);

        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )

        mainViewModel = ViewModelProviders.of(this, MoviesViewModelFactory(
            MainRepository.getInstance(SvapedClient.getInstance())))
            .get(MainViewModel::class.java)

        mainViewModel.loadingState.observe(this,
            Observer { state ->
                state?.let {
                    //progressBar.visibility = if (state) View.VISIBLE else View.GONE
                    //showToast("Loading...")
                }
            })

        mainViewModel.errorState.observe(this,
            Observer { code ->
                code?.let {
                    //emptyView.visibility = View.VISIBLE
                    //emptyView.setMode(code)
                    //showToast("Error")
                }
            })

        mainViewModel.contentState.observe(this,
            Observer { content ->
                if (content != null) {
                    showBikes(content)
                }
            })

        mainViewModel.getBikesNearby()
    }

    fun showBikes(bikes: List<BikeDO>) {
        var imageProviderBike = ImageProvider.fromResource(this, R.mipmap.bike_marker)
        for (bike in bikes) {
            var p = Point(bike.latitude, bike.longitude)
            var bikeMapObject = mapView!!.map.mapObjects.addPlacemark(p, imageProviderBike)
            var imei = bike.imei
            var number = bike.number
            bikeMapObject.addTapListener { mapObject, point ->
                val pop = PopupDialog()
                val fm = supportFragmentManager
                pop.show(fm, "name")
//                Toast.makeText(this.applicationContext, "Imei : $imei\nBike number : $number", Toast.LENGTH_LONG).show()
                true
            }
        }
    }

    fun showParkings(parkings: List<ParkingPointDO>) {
        var parkingProviderBike = ImageProvider.fromResource(this, R.mipmap.parking_marker)
        for (parking in parkings) {
            var p = Point(parking.latitude, parking.longitude)
            var parkingMapObject = mapView!!.map.mapObjects.addPlacemark(p, parkingProviderBike)
            parkingMapObject.addTapListener { mapObject, point ->
                var name = parking.name
                var note = parking.note
                Toast.makeText(this.applicationContext, "Name : $name\nNote : $note", Toast.LENGTH_LONG).show()
                true
            }
        }
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }
}