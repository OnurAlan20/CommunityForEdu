package com.sinamekidev.commforedu.onuralan

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceAround
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sinamekidev.commforedu.ui.theme.Purple200
import com.sinamekidev.commforedu.ui.theme.Purple500

var text = ""

@Composable
fun ForumScreen() {

    val txt1 = remember {
        mutableStateOf("")
    }
    val txt2 = remember {
        mutableStateOf("")
    }
    val txt3 = remember {
        mutableStateOf("")
    }
    val txt4 = remember {
        mutableStateOf("")
    }
    val txt5 = remember {
        mutableStateOf("")
    }
    val txt6 = remember {
        mutableStateOf("")
    }
    val txt7 = remember {
        mutableStateOf("")
    }
    val txt8 = remember {
        mutableStateOf("")
    }
    val txt9 = remember {
        mutableStateOf("")
    }
    val txt10 = remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0XFF202124)) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                ScafoldCompose()
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TxtField()

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 15.dp),
                verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
                BestForumTopicsCard(txt1.value,txt2.value,txt3.value,txt4.value,txt5.value,txt6.value,txt7.value,txt8.value,txt9.value,txt10.value)

            }

        }


    }
}


@Composable
@Preview(showSystemUi = true)
fun ForumScreenTest(){
    ForumScreen()
}

@Composable
fun ScafoldCompose(){
    Scaffold(
        topBar = { TopAppBarCompose()},
        content = {ContentCompose()},

    )

}
//TODO
@Composable
fun ContentCompose(){
    Box(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .padding(15.dp))

}

@Composable
fun TopAppBarCompose(){
    val context = LocalContext.current

    TopAppBar(
        title = { Text("Community Forum", fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context,"Home", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Home, contentDescription = "Back to home screen" )
            }
        },
        actions = {},
        modifier = Modifier.height(60.dp),
        backgroundColor = (topBar),
        contentColor = Color.White

    )

}
@Composable
fun TxtField(){
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier.border(
            BorderStroke(
                width = 4.dp,
                brush = Brush.horizontalGradient(listOf(Purple200, Purple500))
            ),
            shape = RoundedCornerShape(50)
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(Icons.Default.Search, contentDescription ="Search Icon", tint = blueLike  )

        },

        )
}

@Composable
fun BestForumTopicsCard(txt1:String,
                        txt2:String,
                        txt3:String,
                        txt4:String,
                        txt5:String,
                        txt6:String,
                        txt7:String,
                        txt8:String,
                        txt9:String,
                        txt10:String,
                        ){
    Card(modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        border = BorderStroke(width = 1.dp, color = Color.Green),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp), verticalArrangement = Arrangement.SpaceAround) {
            Text(text = "1.$txt1", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "2.$txt2", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "3.$txt3", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "4.$txt4", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "5.$txt5", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "6.$txt6", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "7.$txt7", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "8.$txt8", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "9.$txt9", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "10.$txt10", fontSize = 20.sp, color = Color.LightGray)
        }


    }
}
