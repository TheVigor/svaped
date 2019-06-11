package com.xoxoton.svaped.ui.features.main

import androidx.lifecycle.LiveData
import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.ui.base.BaseViewModel
import com.xoxoton.svaped.ui.common.SingleLiveEvent
import com.xoxoton.svaped.ui.error.EmptyViewMode
import retrofit2.HttpException

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

}