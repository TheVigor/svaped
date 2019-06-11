package com.xoxoton.svaped.ui.features.parking

import androidx.lifecycle.LiveData
import com.xoxoton.svaped.data.model.ParkingPointDO
import com.xoxoton.svaped.ui.base.BaseViewModel
import com.xoxoton.svaped.ui.common.SingleLiveEvent
import com.xoxoton.svaped.ui.error.EmptyViewMode

class ParkingViewModel(private val repository: ParkingRepository): BaseViewModel() {


    private var _loadingState = SingleLiveEvent<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private var _errorState = SingleLiveEvent<Int>()
    val errorState: LiveData<Int>
        get() = _errorState

    private var _contentState = SingleLiveEvent<List<ParkingPointDO>>()
    val contentState: LiveData<List<ParkingPointDO>>
        get() = _contentState

    fun getParkingPoints() {
        addDisposable(repository.getParkingPoints()
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

}