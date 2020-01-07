package com.example.projektmunkaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(){

    private lateinit var mTextViewResult:TextView
    inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextViewResult = findViewById(R.id.text_view_result)

        APIGetRequest()
    }

    fun APIGetRequest(){
        println("Trying to do it!")

        val url = "http://projapi.eu-north-1.elasticbeanstalk.com/ExchangeRateCollector?currencyType=EUR"

        val request =Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body= response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val myType = object : TypeToken<List<CurrencyRate>>(){}.type

                val currencyRates =  gson.fromJson<List<CurrencyRate>>(body,myType)

                println(currencyRates)
            }

            override fun onFailure(call: Call, e: IOException) {
              println("WHY NOT WORKING?!")
            }
        })
    }

    class CurrencyRate(val location:String, val buyingRate:String, val sellingRate:String,val currencyFrom:String,val currencyTo:String)
}
