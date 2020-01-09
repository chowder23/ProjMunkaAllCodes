package com.example.projektmunkaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.projektmunkaandroid.DataStructure.BusinessLogic
import com.example.projektmunkaandroid.DataStructure.ChangeRate
import com.example.projektmunkaandroid.DataStructure.GoogleMapsAPIResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mTextViewResult: TextView
    private var myBsLogic: BusinessLogic = BusinessLogic()
    private lateinit var allRate: List<ChangeRate>
    private var myGoogleMapsAPIResponse:GoogleMapsAPIResponse? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextViewResult = findViewById(R.id.text_view_result)
        allRate = myBsLogic.APIGetRequest("USD")
        println(allRate.toString())
        myGoogleMapsAPIResponse= myBsLogic.GetGoogleMapsAPIResponse()
    }


    fun ToTheMap(view: View){
        val intent = Intent(this,MapsActivity::class.java)
        intent.putExtra("Latitude", myGoogleMapsAPIResponse?.results?.get(0)?.geometry?.location?.lat)
        intent.putExtra("Longitude", myGoogleMapsAPIResponse?.results?.get(0)?.geometry?.location?.lng)
        startActivity(intent)
    }

}
