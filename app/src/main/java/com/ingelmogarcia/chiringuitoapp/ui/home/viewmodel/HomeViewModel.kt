package com.ingelmogarcia.chiringuitoapp.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingelmogarcia.chiringuitoapp.data.model.PedidoItemModel

class HomeViewModel : ViewModel() {

    val _sumaTotalLD = MutableLiveData<Double>()

    val _botonVerPedidoLD = MutableLiveData<Boolean>()

    companion object{
        var sumaTotal: Double = 0.0
        var listaPedido: MutableList<PedidoItemModel> = mutableListOf()
    }
}