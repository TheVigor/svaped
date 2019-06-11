package com.xoxoton.svaped

import android.os.Bundle
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

import com.yandex.mapkit.mapview.MapView

/**
 * В этом примере показывается карта и камера выставляется на указанную точку.
 * Не забудьте запросить необходимые разрешения.
 */
class MapActivity : AppCompatActivity() {
    private val MAPKIT_API_KEY = "f57d302b-98fd-45d5-94c4-4ef2110f517b"
    private val TARGET_LOCATION = Point(47.23135, 39.72328)

    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate,
         * но в данном примере он устанавливается в activity.
         */
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        /**
         * Инициализация библиотеки для загрузки необходимых нативных библиотек.
         * Рекомендуется инициализировать библиотеку MapKit в методе Activity.onCreate
         * Инициализация в методе Application.onCreate может привести к лишним вызовам и увеличенному использованию батареи.
         */
        MapKitFactory.initialize(this)
        // Создание MapView.
        setContentView(R.layout.map)
        super.onCreate(savedInstanceState)
        mapView = findViewById(R.id.mapview);

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )

        mapView!!.map.mapObjects.addPlacemark(TARGET_LOCATION)
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