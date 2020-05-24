package com.example.projektmunkaandroid.DataStructure

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import javax.net.ssl.*


class BusinessLogic {

    private var changeRates = listOf<ChangeRate>()
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
     fun APIGetRequest(CurrencyType:String):List<ChangeRate>{
        println("Trying to do it!")

        val url =
            "https://b0bdeaf9.ngrok.io/api/ExchangeRateCollector?currencyType=$CurrencyType"

        val request = Request.Builder().url(url).build()

        val client = getUnsafeOkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body= response.body()?.string()
                val gson = GsonBuilder().create()
                val myType = object : TypeToken<List<ChangeRate>>(){}.type
                changeRates =  gson.fromJson<List<ChangeRate>>(body,myType)
                println("Succes madafuka!")

            }

            override fun onFailure(call: Call, e: IOException) {
                println("There is a problem sir!")
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

    fun GetGoogleMapsAPIResponse():GoogleMapsAPIResponse? {
        APIGetRequest("EUR")
        while(changeRates.count()<1)
        {

        }
        var myGoogleMapsAPIResponse:GoogleMapsAPIResponse?=null
        val url ="https://maps.googleapis.com/maps/api/geocode/json?address=${changeRates[0].location}&key=AIzaSyCMQLKNHedg2DRQVMXyd8EyQG1_bNSpsKk"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body= response.body()?.string()
                val gson = GsonBuilder().create()
                val myType = object : TypeToken<GoogleMapsAPIResponse>(){}.type
                myGoogleMapsAPIResponse =  gson.fromJson<GoogleMapsAPIResponse>(body,myType)
                println("Succes madafuka!")

            }

            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
            }
        })
        while(myGoogleMapsAPIResponse == null)
        {

        }
        return myGoogleMapsAPIResponse
    }

    override fun toString(): String {
        var output =String()
        for (changerate in changeRates)
        {
            output += "Name: ${changerate.exchangerName}, Location: ${changerate.location}, Buying Rate: ${changerate.buyingRate} \n"
        }

        return output
    }
}