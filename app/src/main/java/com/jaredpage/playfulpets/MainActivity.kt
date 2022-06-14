package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    //declare variables for user login
    var username = ""
    var password = ""

    //Firebase auth used to connect to firebase user authentication
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth variable
        auth = Firebase.auth

        //check if user is already logged in
        val currentUser = auth.currentUser
        if(currentUser != null){
            //create intent which moves to forum activity
            val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
            startActivity(forumIntent)//start activity
        }
    }

    //method for when login button clicked
    fun loginLoginBtnClicked(view: View){
        //assign variables values from UI
        username = loginUsernameText.text.toString()
        password = loginPasswordText.text.toString()

        if (username.isEmpty() || password.isEmpty()){
            loginUsernameText.setError("Please full in your details!")
        }else{

            //calls method used to sign user in through firebase
            firebaseSignIn()
        }


        //create intent which moves to event/maps activity
//        val eventIntent = Intent(this, EventActivity::class.java)//intent allows you to interact with other activites
//        startActivity(eventIntent)//start activity

//        //create intent which moves to forum activity
//        val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
//        startActivity(forumIntent)//start activity
    }

    //method for when sign up button clicked
    fun loginRegisterBtnClicked(view: View){
        //create intent to send user to signup page from the current page
        val signUpIntent = Intent(this, SignUpActivity::class.java)//intent allows you to interact with other activites
        startActivity(signUpIntent)

    }

    private fun firebaseSignIn() {
        //create onCompleteListener to run once user wants to sign in and establishes what happens to them when creation was successful
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener{
            task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                //create intent which moves to forum activity
                val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
                startActivity(forumIntent)//start activity

            }else{
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}