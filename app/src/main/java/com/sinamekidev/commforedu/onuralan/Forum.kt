package com.sinamekidev.commforedu.onuralan

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0XFF202124)) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                ScafoldCompose()
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), horizontalArrangement = Arrangement.Center) {
                TxtField()

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 15.dp)) {

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
        content = {ContentCompose()}
    )

}
@Composable
fun ContentCompose(){
    Box(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth())

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
