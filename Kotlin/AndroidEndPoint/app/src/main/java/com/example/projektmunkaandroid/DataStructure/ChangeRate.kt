package com.example.projektmunkaandroid.DataStructure

class ChangeRate(val ExchangerName:String,val Location:String,var BuyingRate:String,var SellingRate:String,val CurrencyFrom:String,val CurrencyTo:String) {

     fun SetBuyingRate(newBuyingRate:String) {
         BuyingRate = newBuyingRate
     }

    fun SetSellingRate(newSellingRate:String){
        SellingRate=newSellingRate
    }
}