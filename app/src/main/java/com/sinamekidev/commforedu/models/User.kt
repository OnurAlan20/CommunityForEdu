package com.sinamekidev.commforedu.models

data class User(
    var name:String,
    var description:String = "",
    var profile_url:String,
    var uid:String,
    var postList:ArrayList<String> = arrayListOf(),
    var school:String
    )
