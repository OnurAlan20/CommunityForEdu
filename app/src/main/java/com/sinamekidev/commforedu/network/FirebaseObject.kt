package com.sinamekidev.commforedu.network

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sinamekidev.commforedu.models.Post
import com.sinamekidev.commforedu.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object FirebaseObject {
    private lateinit var auth: FirebaseAuth
    private lateinit var currentUser:FirebaseUser
    var user: User? = null
    val db = Firebase.firestore
    fun initFirebase(){
        auth = Firebase.auth
        if(auth.currentUser != null){
            db.collection("Users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
                user = User(it["name"] as String, it["description"] as String,
                    it["profile_url"] as String,it["uid"] as String, it["postList"] as ArrayList<String>
                    ,it["school"] as String
                )
                println("USER INIT SUCCESS")
                println("WELCOME ${user!!.name}")
            }
        }
    }
    fun signUp(name:String,profile_description:String,email:String,
               password:String,school:String,finished:() -> Unit = {}):String{
        var result:String = "Loading"
        GlobalScope.launch {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    result = "Success"
                    initUser(auth.currentUser!!)
                    println("Sign UP:${it.result.user?.email}")
                    println("Login:${currentUser.email}")
                    val new_user = User(name,profile_description,"",
                        auth.currentUser!!.uid, school =school)
                    db.collection("Users").document(new_user.uid).set(new_user).addOnCompleteListener {
                        if(it.isSuccessful){
                            println("DATABASE SIGNUP SUCCESS")
                            user = new_user
                            finished.invoke()
                        }
                        else{
                            println("DATABASE SIGNUP FAILED ${it.exception!!.localizedMessage}")
                        }
                    }.addOnCanceledListener{
                        println("DATABASE SIGNUP FAILED ${it.exception!!.localizedMessage}")
                    }

                }
                else{
                    result = "Failed"
                }
            }.addOnCanceledListener {
                result = "Failed"
            }
        }
        return result
    }
    fun login(email: String,password: String,finished: () -> Unit = {}):String{
        var result:String = "Loading"
        GlobalScope.launch {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    result = "Success"
                    initUser(auth.currentUser!!)
                    db.collection("Users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
                        user = User(it["name"] as String, it["description"] as String,
                            it["profile_url"] as String,it["uid"] as String, it["postList"] as ArrayList<String>
                        ,it["school"] as String
                        )
                        println("USER INIT SUCCESS")
                        println("WELCOME ${user!!.name}")
                    }
                    finished.invoke()
                }
                else{
                    println(it.exception?.localizedMessage)
                    result = "Failed"
                }
            }.addOnCanceledListener {
                result = "Failed"
            }
        }
        return result
    }
    fun initUser(user: FirebaseUser){
        currentUser = user
    }
    fun getUserByID(user_id:String):User{
        var retUser:User = User("","","","", school = "")
        db.collection("Users").document(user_id).get().addOnSuccessListener {
            retUser = User(it["name"] as String, it["description"] as String,
                it["profile_url"] as String,it["uid"] as String, it["postList"] as ArrayList<String>
                ,it["school"] as String
            )
        }
        return retUser
    }
    fun getPosts(mutablePostList: MutableState<ArrayList<Post>>){
        GlobalScope.launch {
            db.collection("Posts").addSnapshotListener{data,error ->
                if (data != null) {
                    for (post in data.documentChanges){
                        post.document
                        var new_post = Post(post.document["author"] as String,post.document["text"] as String,post.document["image_url"] as String)
                        mutablePostList.value.add(new_post)
                    }
                }
            }
        }
    }
    fun addPost(){

    }
}