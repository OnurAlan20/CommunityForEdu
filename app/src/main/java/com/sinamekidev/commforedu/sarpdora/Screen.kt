package com.sinamekidev.commforedu.sarpdora

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sinamekidev.commforedu.CommentButtonFun
import com.sinamekidev.commforedu.LikeButtonFun
import com.sinamekidev.commforedu.R
import com.sinamekidev.commforedu.ShareButtonFun
import com.sinamekidev.commforedu.models.Post
import com.sinamekidev.commforedu.models.User
import com.sinamekidev.commforedu.network.FirebaseObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(){
    var homeBottomBarSelectedIndex = remember {
        mutableStateOf(1)
    }
    var homeTopBarTitle = remember {
        mutableStateOf("Home")
    }
    var navMenuVisibity = remember {
        mutableStateOf(false)
    }
    var navMenuIndex = remember {
        mutableStateOf(0)
    }
    var navController = rememberNavController()
    Box() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f)) {
                HomeTopNavBar(title = homeTopBarTitle.value,
                    navMenuVisibility = navMenuVisibity, modifier = Modifier.fillMaxSize())
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)) {
                Surface(modifier = Modifier.fillMaxSize(), color = SecondBg) {
                    MainNavigation(navController = navController)
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), verticalAlignment = Alignment.Bottom) {
                HomeBottomNavigatonBar(modifier = Modifier.fillMaxSize()){
                    HomeBottomNavBarButton(indexNo = 0, selectedInd = homeBottomBarSelectedIndex,
                        icon = R.drawable.user , text = "Profile", modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .clickable {
                                homeBottomBarSelectedIndex.value = 0
                                navController.navigate("Profile")
                            })
                    HomeBottomNavBarButton(indexNo = 1, selectedInd = homeBottomBarSelectedIndex,
                        icon = R.drawable.home , text = "Home", modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clickable {
                                homeBottomBarSelectedIndex.value = 1
                                navController.navigate("Home")
                            })
                    HomeBottomNavBarButton(indexNo = 2, selectedInd = homeBottomBarSelectedIndex,
                        icon = R.drawable.setting , text = "Settings", modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                homeBottomBarSelectedIndex.value = 2
                                navController.navigate("Settings")
                            })
                }
            }

        }
        AnimatedVisibility(visible =navMenuVisibity.value) {
            Row(modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)) {
                    NavigationMenu {
                        MenuRow(icon = R.drawable.home, text = "Home", isSelected = navMenuIndex.value == 0) {
                            navMenuIndex.value = 0
                        }
                        MenuRow(icon = R.drawable.discussion, text = "Forum",isSelected = navMenuIndex.value == 1) {
                            navMenuIndex.value = 1
                        }
                        MenuRow(icon = R.drawable.mortarboard, text = "Course",isSelected = navMenuIndex.value == 2) {
                            navMenuIndex.value = 2
                        }
                        MenuRow(icon = R.drawable.ranking, text = "Ranking",isSelected = navMenuIndex.value == 3) {
                            navMenuIndex.value = 3
                        }
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
                            Surface(modifier = Modifier.fillMaxWidth(), color = SecondBg) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.width(60.dp))
                                    Text(text = "Sign Out", color = MainBg, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Icon(painter = painterResource(id = R.drawable.logout)
                                        , contentDescription ="Log out", tint = MainBg, modifier = Modifier.size(30.dp))
                                }
                            }
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()) {
                    Surface(color = Color(0x8B070707), modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navMenuVisibity.value = false
                        }) {

                    }
                }
            }
        }
    }


}
@Composable
fun HomeBottomNavigatonBar(modifier: Modifier,content:@Composable () -> Unit){
    BottomAppBar(modifier=modifier,backgroundColor = MainBg, elevation = 25.dp) {
        Row(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
@Composable
fun HomeTopNavBar(modifier: Modifier = Modifier,title:String,navMenuVisibility:MutableState<Boolean>){
    TopAppBar(modifier = modifier, elevation = 25.dp, backgroundColor = MainBg) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector =Icons.Default.Menu , contentDescription ="Menu", tint = FontColor,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        navMenuVisibility.value = true
                    })
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(32.dp))
            }

        }
    }
}
@Composable
fun HomeBottomNavBarButton(modifier: Modifier = Modifier,
                           indexNo:Int,
                           selectedInd:MutableState<Int>,
                           icon:Int,
                           text:String){
    var colorTint = remember {
        mutableStateOf(SecondBg)
    }
    if (indexNo == selectedInd.value){
        colorTint.value = FontColor
    }
    else{
        colorTint.value = SecondBg
    }
    Surface(modifier = modifier, color = Color.Transparent) {
        Column(modifier = Modifier
            .padding(4.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = icon), contentDescription = "Icon", tint = colorTint.value)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text, color = colorTint.value, fontWeight = FontWeight.Normal)
        }
    }
}
@Composable
fun NavigationMenu(content: @Composable () -> Unit){
    Surface(modifier = Modifier.fillMaxSize(), color = SelectedBg) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="Logo", contentScale = ContentScale.FillWidth)
            content()
        }
    }
}
@Composable
fun MenuRow(icon:Int,text:String,isSelected:Boolean = false,onClick: () -> Unit){
    var rowColor = remember {
        mutableStateOf(SelectedBg)
    }
    if(isSelected){
        rowColor.value = Color(0xFFB1573A)
    }
    else{
        rowColor.value = SelectedBg
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick.invoke() }, backgroundColor = rowColor.value) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(60.dp))
                Icon(painter = painterResource(id = icon), contentDescription = "Icon", tint = FontColor, modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = text, color = FontColor, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
@Preview
@Composable
fun ScreenPreview(){
    HomeScreen()
}

@Composable
fun MainNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeNavigation()
        }
        composable("Profile"){
            ProfileNavigation()
        }
        composable("Settings"){
            SettingsNavigation()
        }
    }
}

@Composable
fun HomeNavigation(){
    var el = Post("","","")
    var posts = remember {
        mutableStateListOf(el)
    }
    FirebaseObject.getPosts(posts)
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                LazyColumn(){
                    items(posts){
                        if(!it.author.equals("")) {
                            HomePostLayout(it)
                            LikeShareCommentSection()
                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MainBg)
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun ProfileNavigation(){
    var post_text = remember{
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            CreatePostSection(post_text)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.End) {
                Button(onClick = {
                    FirebaseObject.addPost(
                        Post(FirebaseObject.user!!.uid,post_text.value,"")){
                        post_text.value = ""
                    }
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MainBg), shape = RoundedCornerShape(25.dp)) {
                    Text(text = "Share", color = FontColor, fontSize = 20.sp)
                }
            }
        }
    }
}
@Composable
fun CreatePostSection(post_text:MutableState<String>){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 9.dp), color = Color.Transparent,
        ){
        Column(modifier = Modifier
            .fillMaxHeight(0.3f)
            .padding(horizontal = 10.dp)) {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = post_text.value, onValueChange = {
                    post_text.value = it
                }, leadingIcon= {
                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp)) {
                        Image(painter = painterResource(id = R.drawable.profile),
                            contentDescription = "", modifier = Modifier.size(52.dp))
                    }
                }, placeholder = {
                    Row() {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Create post....", color = FontColor, fontSize = 26.sp)
                    }
                },modifier = Modifier.fillMaxSize())

            }
        }
    }
}

@Composable
fun SettingsNavigation(){
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Settings Navigation Screen", color = Color.White)
        }
    }
}
@Composable
fun SharePostButton(onCick:() -> Unit = {}){
    FloatingActionButton(onClick = { onCick.invoke() },
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 15.dp),
        backgroundColor = MainBg) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Icon", tint = Color.White)
    }
}

@Composable
fun HomePostLayout(post: Post){
    var imageOk = post.image_url != null
    var textOk = post.text != null
    var show = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth(), color = Color.Transparent) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Row() {
                Image(painter = painterResource(id = R.drawable.profile), contentDescription ="", modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(52.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Column() {
                    var postUser = remember {
                        mutableStateOf(User("","","","", school = ""))
                    }
                    println("USER ID: ${post.author}")
                    FirebaseObject.getUserByID(post.author){
                        postUser.value = it
                        show.value = true
                    }
                    AnimatedVisibility(show.value){
                        Text(text = postUser.value.name, color = FontColor, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                        Text(text = postUser.value.description, color = Color(0xE8B6B6B6), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if(textOk){
                post.text?.let {
                    Text(text = it,
                        color = FontColor, fontSize = 20.sp)
                }
            }

            if(imageOk){
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }

}
@Composable
fun LikeShareCommentSection(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(9.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth(0.3f)
            .clickable {
                LikeButtonFun()
            }) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.like), contentDescription ="Like",
                    modifier = Modifier.size(18.dp), colorFilter = ColorFilter.tint(MainBg))
                Text(text = "Like", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MainBg)
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth(0.5f)
            .clickable {
                CommentButtonFun()
            }) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.comment), contentDescription ="Comment",
                    modifier = Modifier.size(18.dp), colorFilter = ColorFilter.tint(MainBg))
                Text(text = "Comment", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MainBg)
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ShareButtonFun()
            }) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.share), contentDescription ="Share",
                    modifier = Modifier.size(18.dp), colorFilter = ColorFilter.tint(MainBg))
                Text(text = "Share", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MainBg)
            }
        }
    }
}