package com.example.projektmunkaandroid.DataStructure

class ChangeRate(val exchangerName:String,val location:String,var buyingRate:String,var sellingRate:String,val currencyFrom:String,val currencyTo:String) {

    fun SetBuyingRate(newBuyingRate:String) {
         buyingRate = newBuyingRate
    }

    fun SetSellingRate(newSellingRate:String){
        sellingRate=newSellingRate
    }
}