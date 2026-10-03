package com.bosandroidapp.aopayfinance.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bosandroidapp.aopayfinance.data.repository.OnlineEnachRepository
import com.bosandroidapp.aopayfinance.ui.viewmodel.OnlineEnachViewModel

class OnlineEnachViewModelFactory(private val repository: OnlineEnachRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OnlineEnachViewModel::class.java) -> OnlineEnachViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
