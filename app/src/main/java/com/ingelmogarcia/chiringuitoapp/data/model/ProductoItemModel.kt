package com.ingelmogarcia.chiringuitoapp.data.model

class ProductoItemModel{

    var urlImage: String
    var name: String
    var price: Double

    constructor(urlImage: String, name: String, price: Double){
        this.urlImage = urlImage
        this.name = name + " [" + price + "€]"
        this.price = price
    }

}