package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    //Firebase connection
    private lateinit var database: DatabaseReference
    //declare variables for user login
    var username = ""
    var password = ""

    //Firebase auth used to connect to firebase user authentication
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        database = Firebase.database.reference

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
            firebaseSignIn(username)
        }

    }

    //method for when sign up button clicked
    fun loginRegisterBtnClicked(view: View){
        //create intent to send user to signup page from the current page
        val signUpIntent = Intent(this, SignUpActivity::class.java)//intent allows you to interact with other activites
        startActivity(signUpIntent)

    }

    private fun firebaseSignIn(userName: String) {
        //create
        database.child("users").child(userName).get().addOnSuccessListener {
            if (it.exists()){
                //get all data fields from specified user
                val username = it.child("userName").value
                val email = it.child("email").value
                val userpfp = it.child("userpfp").value

                //toast message to display successful login
                Toast.makeText(this, "Successfully login!", Toast.LENGTH_LONG).show()
                //create intent to send user to forum page from the current page
                val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
                startActivity(forumIntent)
            }else{
                Toast.makeText(this, "User does not exist!", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
        }

    }
}