package com.sinamekidev.commforedu.models

import org.w3c.dom.Text

data class Post(var author:String,
                var text:String?,
                var image_url:String?,
                var like_list:ArrayList<String> = arrayListOf(),
                )
