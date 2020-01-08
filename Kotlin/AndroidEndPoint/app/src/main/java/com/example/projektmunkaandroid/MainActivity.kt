package com.example.projektmunkaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.projektmunkaandroid.DataStructure.BusinessLogic
import com.example.projektmunkaandroid.DataStructure.ChangeRate
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mTextViewResult: TextView
    private var myBsLogic: BusinessLogic = BusinessLogic()
    private lateinit var allRate: List<ChangeRate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextViewResult = findViewById(R.id.text_view_result)
        allRate = myBsLogic.APIGetRequest("USD")
        println(allRate.toString())
        Nothing()


    }


    fun Nothing() {
        runOnUiThread {mTextViewResult.text = myBsLogic.toString()}
    }
}
