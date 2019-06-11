package com.xoxoton.svaped.ui.features.main.login

import androidx.lifecycle.LiveData
import com.xoxoton.svaped.ui.base.BaseViewModel
import com.xoxoton.svaped.ui.common.SingleLiveEvent
import com.xoxoton.svaped.ui.error.EmptyViewMode

class LogInViewModel(private val repository: LoginRepository) : BaseViewModel() {
    private var _loadingState = SingleLiveEvent<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState
    private var _errorState = SingleLiveEvent<Int>()
    val errorState: LiveData<Int>
        get() = _errorState

    private var _contentState = SingleLiveEvent<String>()
    val contentState: LiveData<String>
        get() = _contentState

    fun login(login: String, pass: String) {
        addDisposable(repository.login(login, pass)
            .doOnSubscribe { _loadingState.value = true }
            .doAfterTerminate { _loadingState.value = false }
            .subscribe({
                if (it.isEmpty()) {
                    _errorState.value = EmptyViewMode.MODE_INCORRECT_LOGIN
                    return@subscribe
                }
                _contentState.value = it
            }, {
                _errorState.value = EmptyViewMode.MODE_INCORRECT_LOGIN
            })
        )
    }

}