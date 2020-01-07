package com.example.projektmunkaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.projektmunkaandroid.DataStructure.BusinessLogic
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(){

    private lateinit var mTextViewResult:TextView
    private  var myBsLogic: BusinessLogic = BusinessLogic()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextViewResult = findViewById(R.id.text_view_result)
        myBsLogic.APIGetRequest("USD")



    }

}
