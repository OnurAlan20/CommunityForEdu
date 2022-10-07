package com.sinamekidev.commforedu.onuralan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
var community1 = "HSD"
var community2 = "YAZGİT"
var community3 = "İEEE"
var community4 = "EMBS"
var community5 = "CS"
var community6 = "AUCC"
var community7 = "AUDSC"
var community8 = "DSC"
var community9 = "AI"
var community10 = "Robot Bilim"

val mutableListOfRanking = mutableListOf<String>(community1, community2,community3, community4,
    community5, community6, community7, community8, community9, community10)
@Composable
fun RankingScreen(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), color = Color(0XFF202124)) {
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                ScafoldCompose("Communities Rankings")

            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun RankingScreenTest(){
    RankingScreen()
}
@Composable
fun BestCommunities(    community1:String,
                        community2:String,
                        community3:String,
                        community4:String,
                        community5:String,
                        community6:String,
                        community7:String,
                        community8:String,
                        community9:String,
                        community10:String,
){
    Card(modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        border = BorderStroke(width = 1.dp, color = Color.Green),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp), verticalArrangement = Arrangement.SpaceAround) {
            Text(text = "1.$community1", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "2.$community2", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "3.$community3", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "4.$community4", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "5.$community5", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "6.$community6", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "7.$community7", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "8.$community8", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "9.$community9", fontSize = 20.sp, color = Color.LightGray)
            Text(text = "10.$community10", fontSize = 20.sp, color = Color.LightGray)
        }


    }
}
