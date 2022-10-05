package com.sinamekidev.commforedu.onuralan

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.outlined.AccountBox

import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sinamekidev.commforedu.MainActivity
import com.sinamekidev.commforedu.network.FirebaseObject

//Login Screen
@Composable
fun LoginScreen(navController: NavController){
    val context = LocalContext.current
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF202124)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 30.dp, start = 15.dp, end = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            SignInCard()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Community For Edu",
                color = Color(0xFFd92643),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            AccountInfo(email, password)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 30.dp, end = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    onClick =
                    {
                       FirebaseObject.login(email.value,password.value){
                           val intent = Intent(context,MainActivity::class.java)
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                           context.startActivity(intent)
                       }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFd92643)))

                {
                    Text(text = "Sign In",color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))

                Divider(thickness = 2.dp, color = Color.White)

                Column(modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxSize(), horizontalAlignment = Alignment.End) {
                    Text(text = "Don't Have an Account?",color = Color(0xFFd92643),fontWeight = FontWeight.Bold, fontSize = 18.sp,
                        modifier = Modifier.clickable {
                            navController.navigate("register")
                        })
                    //yorum

                }

            }










        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenTest (){
    val context = LocalContext.current
    LoginScreen(navController = NavController(context))
}
@Composable
fun SignInCard(){
    Card(
        modifier = Modifier.size(200.dp),
        elevation = 2.dp
    ) {
        Image(
            painterResource(com.sinamekidev.commforedu.R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
fun AccountInfo (email:MutableState<String>,password:MutableState<String>){
    Column(modifier = Modifier.padding(start = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.Email, contentDescription ="Email", tint = Color(0xFFAE7CD1),
                modifier = Modifier.size(25.dp) )
            Spacer(modifier = Modifier.width(5.dp))
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Enter Email", color = Color(0xFFAE7CD1)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.AccountBox, contentDescription ="Email", tint = Color(0xFFAE7CD1),
                modifier = Modifier.size(25.dp) )
            Spacer(modifier = Modifier.width(5.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Enter Password", color = Color(0xFFAE7CD1)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )


        }



    }
}

//---------------------------------------------------------------
//Register Screen
@Composable
fun RegisterScreen(navController: NavController){
    var name = remember{
        mutableStateOf("")
    }
    var school = remember{
        mutableStateOf("")
    }
    var registerEmail = remember{
        mutableStateOf("")
    }
    var password = remember{
        mutableStateOf("")
    }
    var passwordRep =remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF202124)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, bottom = 30.dp, start = 15.dp, end = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            SignInCard()

            Text(text = "Please complete the part below.", modifier = Modifier.padding(top = 15.dp),color = Color(0xFF64254b),fontWeight = FontWeight.Bold, fontSize = 22.sp)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Person, contentDescription ="Name", tint = Color(0xFFAE7CD1),
                    modifier = Modifier.size(25.dp) )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    value = name.value,
                    onValueChange = {name.value = it},
                    label = { Text("Enter Name ", color = Color(0xFFAE7CD1)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Person, contentDescription ="Surname", tint = Color(0xFFAE7CD1),
                    modifier = Modifier.size(25.dp) )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    value = school.value,
                    onValueChange = { school.value = it },
                    label = { Text("Enter School ", color = Color(0xFFAE7CD1)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Email, contentDescription ="RegisterEmail", tint = Color(0xFFAE7CD1),
                    modifier = Modifier.size(25.dp) )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    value = registerEmail.value,
                    onValueChange = { registerEmail.value = it },
                    label = { Text("Enter Email ", color = Color(0xFFAE7CD1)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Star, contentDescription ="Password", tint = Color(0xFFAE7CD1),
                    modifier = Modifier.size(25.dp) )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Enter Password", color = Color(0xFFAE7CD1)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Star, contentDescription ="RepPassword", tint = Color(0xFFAE7CD1),
                    modifier = Modifier.size(25.dp) )
                Spacer(modifier = Modifier.width(5.dp))
                TextField(
                    value = passwordRep.value,
                    onValueChange = { passwordRep.value = it },
                    label = { Text("Enter Password Again", color = Color(0xFFAE7CD1)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Button(modifier = Modifier
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .height(40.dp),
                onClick = {
                     if((name.value.isNullOrEmpty() || school.value.isNullOrEmpty()) || (registerEmail.value.isNullOrEmpty() || password.value.isNullOrEmpty())){
                         Toast.makeText(context,"Fields can not be empty!",Toast.LENGTH_SHORT).show()
                     }
                    else{
                        if(password.value.equals(passwordRep.value)){
                            FirebaseObject.signUp(name.value,"", registerEmail.value,password.value,school.value){
                                val intent = Intent(context,MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                context.startActivity(intent)
                            }
                        }
                         else{
                            Toast.makeText(context,"Passwords not match",Toast.LENGTH_SHORT).show()
                        }
                     }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan))
            {
                Text(text = "Register", color = Color.Black,fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    }
}
