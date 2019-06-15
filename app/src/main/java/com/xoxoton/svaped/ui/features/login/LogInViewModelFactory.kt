package com.xoxoton.svaped.ui.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xoxoton.svaped.ui.features.login.LogInViewModel
import com.xoxoton.svaped.ui.features.login.LoginRepository

@Suppress("UNCHECKED_CAST")
class LogInViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogInViewModel(repository) as T
    }
}