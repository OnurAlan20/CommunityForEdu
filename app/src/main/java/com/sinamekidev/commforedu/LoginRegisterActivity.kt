package com.sinamekidev.commforedu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sinamekidev.commforedu.network.FirebaseObject
import com.sinamekidev.commforedu.onuralan.LoginScreen
import com.sinamekidev.commforedu.onuralan.RegisterScreen
import com.sinamekidev.commforedu.ui.theme.CommForEduTheme

class LoginRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseObject.initFirebase{
            if(FirebaseObject.user != null){
                val intent = Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            CommForEduTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginNavigation()
                }
            }
        }
    }
    @Composable
    fun LoginNavigation(){
        val navController = rememberNavController()
        if(FirebaseObject.user != null){
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }else{
            NavHost(navController = navController, startDestination = "login"){
                composable("login"){
                    LoginScreen(navController)
                }
                composable("register"){
                    RegisterScreen(navController)
                }
            }
        }

    }
}


