package com.example.projektmunkaandroid.DataStructure


class GoogleMapsAPIResponse(var results:List<Results>,var status:String) {

}

class Results(var adress_Components:List<addressComponent>,var formatted_Address:String,var geometry:Geometry, var place_id:String,var types:List<String>)

class Geometry(var bounds:Bound,var location:LatLngPair,var location_type:String,var viewport:Bound)

class Bound(var northeast:LatLngPair,var soutwest:LatLngPair)

class LatLngPair(var lat:Double,var lng:Double)

class addressComponent(var long_name:String,var short_name:String,var types:List<String>)
