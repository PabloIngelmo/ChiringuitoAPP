package com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _sumaTotal = MutableLiveData<Double>()
    val sumaTotal:LiveData<Double> get() = _sumaTotal

    fun aa(a:Double){
        _sumaTotal.postValue(a)
    }
}