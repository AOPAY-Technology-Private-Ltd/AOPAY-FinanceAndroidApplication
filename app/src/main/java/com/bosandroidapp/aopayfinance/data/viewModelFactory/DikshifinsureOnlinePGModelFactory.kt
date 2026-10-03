package com.bosandroidapp.aopayfinance.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bosandroidapp.aopayfinance.data.repository.DikshifinsureRepository
import com.bosandroidapp.aopayfinance.ui.viewmodel.DikshifinsureViewModel


class DikshifinsureOnlinePGModelFactory (private val repository: DikshifinsureRepository): ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(DikshifinsureViewModel::class.java) -> DikshifinsureViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }


}