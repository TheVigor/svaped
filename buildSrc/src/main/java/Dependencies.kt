object ApplicationId {
    val id = "com.xoxoton.svaped"
}

object Modules {
    val app = ":app"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.31"
    val gradle = "3.4.1"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    val material = "1.0.0"

    val lifecycleExtensions = "2.0.0"
    val lifecycleCompiler = "2.0.0"

    val appCompat = "1.0.2"
    val coreKtx = "1.0.2"

    val constraintLayout = "1.1.3"

    val junit = "4.12"
    val androidTestRunner = "1.2.0"
    val espressoCore = "3.2.0"

    val rxKotlin = "2.3.0"
    val rxAndroid = "2.1.1"

    val retrofit = "2.5.0"
    val retrofitGson = "2.5.0"
    val retrofitRxJava = "2.5.0"

    val yandexMapKit = "3.3.1"

    val okHttp = "3.12.1"

    val koin = "2.0.0"

    val bottomNavigationViewEx = "2.0.4"
}

object Libs {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitRxJava}"

    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    val yandexMapKit = "com.yandex.android:mapkit:${Versions.yandexMapKit}"
    val yandexDirections = "com.yandex.android:directions:${Versions.yandexMapKit}"
    val yandexPlaces = "com.yandex.android:places:${Versions.yandexMapKit}"
    val yandexSearch = "com.yandex.android:search:${Versions.yandexMapKit}"
    val yandexTransport = "com.yandex.android:transport:${Versions.yandexMapKit}"

    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    val bottomNavigationViewEx =
        "com.github.ittianyu:BottomNavigationViewEx:${Versions.bottomNavigationViewEx}"
}

object KotlinLibs {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibs {
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val material = "com.google.android.material:material:${Versions.material}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleCompiler}"
}

object TestLibs {
    val junit = "junit:junit:${Versions.junit}"

    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
}

