package com.xoxoton.svaped.ui.features.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.data.model.ParkingPointDO

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xoxoton.svaped.data.model.BikeCategory
import com.xoxoton.svaped.ui.base.BaseActivity
import com.xoxoton.svaped.ui.extensions.showToast
import com.xoxoton.svaped.ui.features.login.LoginActivity
import com.xoxoton.svaped.ui.features.parking.ParkingViewModel
import com.xoxoton.svaped.util.DeviceUtil

import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    companion object {
        private const val MAPKIT_API_KEY = "f57d302b-98fd-45d5-94c4-4ef2110f517b"
        private val TARGET_LOCATION = Point(47.23135, 39.72328)

        private const val GREEN_CATEGORY_ZINDEX = 10f
        private const val YELLOW_CATEGORY_ZINDEX = 20f
        private const val RED_CATEGORY_ZINDEX = 30f

        private const val ARG_BOTTOM_BAR_POSITION = "pos"
    }

    private val mainViewModel: MainViewModel by viewModel()
    private val parkingViewModel: ParkingViewModel by viewModel()

    private lateinit var greenBikeIcon: ImageProvider
    private lateinit var yellowBikeIcon: ImageProvider
    private lateinit var redBikeIcon: ImageProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        checkAuth()
        initMapKit()

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        initToolbar()
        initBottomNavigationView(savedInstanceState)

        initMapView()

        initRefreshFab()

        initMainViewModel()
        initParkingViewModel()
    }

    private fun initRefreshFab() {
        refresh_fab.setOnClickListener {
            map_view.map.mapObjects.clear()
            mainViewModel.getBikesNearby()
            parkingViewModel.getParkingPoints()
        }
    }

    override fun onResume() {
        super.onResume()
        checkAuth()
    }

    private fun initMapView() {
        map_view.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0.1f),
            null
        )

        greenBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_green_bike)
        yellowBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_yellow_bike)
        redBikeIcon = ImageProvider.fromResource(this, R.mipmap.ic_red_bike)
    }

    private fun initBottomNavigationView(savedInstanceState: Bundle?) {
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
        bottom_navigation_view.setOnNavigationItemReselectedListener(this)

        val itemId: Int = savedInstanceState?.getInt(ARG_BOTTOM_BAR_POSITION) ?: 0
        bottom_navigation_view.selectedItemId = itemId

        when (itemId) {
            R.id.nav_item_home -> goToHome()
        }
    }


    private fun initMapKit() {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(ARG_BOTTOM_BAR_POSITION, bottom_navigation_view.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_settings) {
            goToSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_home -> goToHome()
        }

        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
    }


    fun initMainViewModel() {
        mainViewModel.loadingState.observe(this,
            Observer { state ->
                state?.let {
                    refresh_progress_bar.visibility = if (state) View.VISIBLE else View.GONE
                    if (state) refresh_fab.hide() else refresh_fab.show()
                }
            })

        mainViewModel.errorState.observe(this,
            Observer { code ->
                code?.let {
                    //emptyView.visibility = View.VISIBLE
                    //emptyView.setMode(code)
                    showToast("Connection error...")
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
                    refresh_progress_bar.visibility = if (state) View.VISIBLE else View.GONE
                    if (state) refresh_fab.hide() else refresh_fab.show()
                }
            })

        parkingViewModel.errorState.observe(this,
            Observer { code ->
                code?.let {
                    //emptyView.visibility = View.VISIBLE
                    //emptyView.setMode(code)
                    showToast("Connection error...")
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