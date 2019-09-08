package com.xoxoton.svaped.ui.features.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.data.model.ParkingPointDO

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import androidx.lifecycle.Observer
import com.xoxoton.svaped.data.model.BikeCategory
import com.xoxoton.svaped.ui.base.BaseActivity
import com.xoxoton.svaped.ui.features.login.LoginActivity
import com.xoxoton.svaped.ui.features.parking.ParkingViewModel

import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(0) {

    companion object {
        private const val MAPKIT_API_KEY = "f57d302b-98fd-45d5-94c4-4ef2110f517b"
        private val TARGET_LOCATION = Point(47.23135, 39.72328)

        private const val GREEN_CATEGORY_ZINDEX = 10f
        private const val YELLOW_CATEGORY_ZINDEX = 20f
        private const val RED_CATEGORY_ZINDEX = 30f

    }

    private val mainViewModel: MainViewModel by viewModel()
    private val parkingViewModel: ParkingViewModel by viewModel()

    private lateinit var greenBikeIcon: ImageProvider
    private lateinit var yellowBikeIcon: ImageProvider
    private lateinit var redBikeIcon: ImageProvider

    override fun onCreate(savedInstanceState: Bundle?) {

        if (!authPrefs.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setupBottomNavigation()

        iv_logout.setOnClickListener {
            goToLogin()
        }

        map_view.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0.1f),
            null
        )

        initMainViewModel()
        initParkingViewModel()

        greenBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_green_bike)
        yellowBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_yellow_bike)
        redBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_red_bike)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        map_view.onStart()
    }

    override fun onStop() {
        map_view.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
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

    fun getPlacemarkInfoByCategory(category: BikeCategory): Pair<ImageProvider, Float> {
        return when(category) {
            BikeCategory.GREEN -> greenBikeIcon to GREEN_CATEGORY_ZINDEX
            BikeCategory.YELLOW -> yellowBikeIcon to YELLOW_CATEGORY_ZINDEX
            BikeCategory.RED -> redBikeIcon to RED_CATEGORY_ZINDEX
        }
    }

    fun showBikes(bikes: List<BikeDO>) {
        val imageProviderBike = ImageProvider.fromResource(this, R.mipmap.bike_marker)

        bikes.forEach {
            val p = Point(it.latitude, it.longitude)

            val placemarkInfo = getPlacemarkInfoByCategory(it.getBikeCategory())

            val bikeMapObject = map_view.map.mapObjects.addPlacemark(p, placemarkInfo.first)
            bikeMapObject.zIndex = placemarkInfo.second

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
}