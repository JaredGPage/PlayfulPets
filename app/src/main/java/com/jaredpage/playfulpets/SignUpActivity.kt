package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.UserModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.Exception
import java.util.*

class SignUpActivity : AppCompatActivity() {

    //Firebase connection
    private lateinit var dbref: DatabaseReference
    //declare variables for user registeration
    var username = ""
    var email = ""
    var password = ""
    var checkPassword = ""
    var userpfp = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase connection
        dbref = Firebase.database.reference
    }

    override fun onStart() {
        super.onStart()

    }

    //method for registering button
    fun registerSignUpBtnClicked(view: View){

        performAuth()

    }

    //method to check users entererd details
    private fun performAuth() {
        //set all the variables to their values
        //create uniques id
        var uniqueID = UUID.randomUUID().toString()

        username = registerUsernameText.text.toString()
        email = registerEmailText.text.toString()
        userpfp = "default"
        password = registerPasswordText.text.toString()
        checkPassword = registerPasswordRetypeText.text.toString()

        if(!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"))){
            registerEmailText.setError("Enter correct email!")
        }else if(password.isEmpty() || password.length < 6){
            registerPasswordText.setError("Enter proper password")
        } else if(!password.equals(checkPassword)){
            registerPasswordRetypeText.setError("Passwords don't match!")
        } else{
            //call method to sign up user
            firebaseSignUp(username, uniqueID, email, userpfp, password)
        }

    }

    private fun firebaseSignUp(userName: String, userID: String, email: String, userpfp: String, password: String) {
        //create model of user and write to database with that users details

        try {
            //make user in UserModel
            val user = UserModel(userName, userID, email, userpfp, password)
            //write to firebase
            dbref.child("users").child(userName).setValue(user)
            //create intent to send user to signup page from the current page
            val mainIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
            startActivity(mainIntent)

        }catch (e : Exception){
            //if there is an error it will display as a toast message
            Toast.makeText(this, "An error occured when uploading data!", Toast.LENGTH_LONG).show()
        }

        }
    }


