package com.xoxoton.svaped.ui.features.parking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ParkingViewModelFactory(private val repository: ParkingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParkingViewModel(repository) as T
    }
}