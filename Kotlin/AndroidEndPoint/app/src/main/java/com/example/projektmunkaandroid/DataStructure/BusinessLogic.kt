package com.example.projektmunkaandroid.DataStructure

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class BusinessLogic {

    private var changeRates = listOf<ChangeRate>()



     fun APIGetRequest(CurrencyType:String):List<ChangeRate>{
        println("Trying to do it!")

        val url =
            "http://projapi.eu-north-1.elasticbeanstalk.com/ExchangeRateCollector?currencyType=$CurrencyType"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body= response.body()?.string()
                val gson = GsonBuilder().create()
                val myType = object : TypeToken<List<ChangeRate>>(){}.type
                changeRates =  gson.fromJson<List<ChangeRate>>(body,myType)
                println("Succes madafuka!")

            }

            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
            }
        })
        return changeRates
    }

    inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    fun GetAllChangeRate():List<ChangeRate>
    {
        return changeRates
    }

    override fun toString(): String {
        var output =String()
        for (changerate in changeRates)
        {
            output += "Name: ${changerate.ExchangerName}, Location: ${changerate.Location}, Buying Rate: ${changerate.BuyingRate} \n"
        }

        return output
    }
}