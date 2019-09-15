package com.xoxoton.svaped.ui.features.main

import androidx.lifecycle.LiveData
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.ui.base.BaseViewModel
import com.xoxoton.svaped.ui.common.SingleLiveEvent
import com.xoxoton.svaped.ui.error.EmptyViewMode
import retrofit2.HttpException

enum class MapMode {
    ONLY_BIKES {
        override fun nextMode() = ONLY_PARKINGS
    },
    ONLY_PARKINGS {
        override fun nextMode() = ALL
    },
    ALL {
        override fun nextMode() = ONLY_BIKES
    };
    abstract fun nextMode(): MapMode
}

class MainViewModel(private val repository: MainRepository): BaseViewModel() {

    private var _loadingState = SingleLiveEvent<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private var _errorState = SingleLiveEvent<Int>()
    val errorState: LiveData<Int>
        get() = _errorState

    private var _contentState = SingleLiveEvent<List<BikeDO>>()
    val contentState: LiveData<List<BikeDO>>
        get() = _contentState

    private var _mapMode = SingleLiveEvent<MapMode>()
    val mapMode: LiveData<MapMode>
        get() = _mapMode

    init {
        _mapMode.value = MapMode.ALL
    }

    fun getBikesNearby() {
        addDisposable(repository.getBikesNearby()
            .doOnSubscribe { _loadingState.value = true }
            .doAfterTerminate { _loadingState.value = false }
            .subscribe({
                if (it.isEmpty()) {
                    _errorState.value = EmptyViewMode.MODE_NO_BIKES
                    return@subscribe
                }
                _contentState.value = it
            }, { _errorState.value = EmptyViewMode.MODE_NO_BIKES }))
    }

    fun updateMode() {
        _mapMode.value = _mapMode.value?.nextMode()
    }

}