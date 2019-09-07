package com.xoxoton.svaped.ui.features.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.data.model.ParkingPointDO

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import androidx.lifecycle.Observer
import com.xoxoton.svaped.ui.features.parking.ParkingViewModel

import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.map.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * В этом примере показывается карта и камера выставляется на указанную точку.
 * Не забудьте запросить необходимые разрешения.
 */
class MapActivity : AppCompatActivity() {
    private val MAPKIT_API_KEY = "f57d302b-98fd-45d5-94c4-4ef2110f517b"

    private val TARGET_LOCATION = Point(47.23135, 39.72328)
    private val ADD_LOCATION = Point(47.23235, 39.72428)

    val mainViewModel: MainViewModel by viewModel()
    val parkingViewModel: ParkingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)

        setContentView(R.layout.map)
        super.onCreate(savedInstanceState)

        map_view.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0.1f),
            null
        )

        initMainViewModel()
        initParkingViewModel()

    }

    fun initMainViewModel() {
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

    fun initParkingViewModel() {
        parkingViewModel.loadingState.observe(this,
            Observer { state ->
                state?.let {
                    //progressBar.visibility = if (state) View.VISIBLE else View.GONE
                    //showToast("Loading...")
                }
            })

        parkingViewModel.errorState.observe(this,
            Observer { code ->
                code?.let {
                    //emptyView.visibility = View.VISIBLE
                    //emptyView.setMode(code)
                    //showToast("Error")
                }
            })

        parkingViewModel.contentState.observe(this,
            Observer { content ->
                if (content != null) {
                    showParkings(content)
                }
            })

        parkingViewModel.getParkingPoints()
    }
    
    fun showBikes(bikes: List<BikeDO>) {
        val imageProviderBike = ImageProvider.fromResource(this, R.mipmap.bike_marker)

        bikes.forEach {
            val p = Point(it.latitude, it.longitude)
            val bikeMapObject = map_view.map.mapObjects.addPlacemark(p, imageProviderBike)
            val imei = it.imei
            val number = it.number
            bikeMapObject.addTapListener { mapObject, point ->
                Toast.makeText(this.applicationContext, "Imei : $imei\nBike number : $number", Toast.LENGTH_LONG).show()
                true
            }
        }

    }

    fun showParkings(parkings: List<ParkingPointDO>) {
        val parkingProviderBike = ImageProvider.fromResource(this, R.mipmap.parking_marker)

        parkings.forEach {
            val p = Point(it.latitude, it.longitude)
            val parkingMapObject = map_view.map.mapObjects.addPlacemark(p, parkingProviderBike)
            parkingMapObject.addTapListener { mapObject, point ->
                val name = it.name
                val note = it.note
                Toast.makeText(this.applicationContext, "Name : $name\nNote : $note", Toast.LENGTH_LONG).show()
                true
            }
        }
    }

    override fun onStop() {
        map_view.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        map_view.onStart()
    }
}