package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_map.*

class MainActivity : AppCompatActivity() {

    //Firebase connection
    private lateinit var database: DatabaseReference
    //declare variables for user login fields entered
    var usernameInput = ""
    var passwordInput = ""
    //variables for logged in user
    private var username = ""
    private var userID = ""
    private var email = ""
    private var userpfp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase connection
        database = Firebase.database.reference

    }

    //method for when login button clicked
    fun loginLoginBtnClicked(view: View){
        //assign variables values from UI
        usernameInput = loginUsernameText.text.toString()
        passwordInput = loginPasswordText.text.toString()

        if (usernameInput.isEmpty() || passwordInput.isEmpty()){
            loginUsernameText.setError("Please full in your details!")
        }else{

            //calls method used to sign user in through firebase
            firebaseSignIn(view, usernameInput)

        }

    }

    //method for when sign up button clicked
    fun loginRegisterBtnClicked(view: View){
        //create intent to send user to signup page from the current page
        val signUpIntent = Intent(this, SignUpActivity::class.java)//intent allows you to interact with other activites
        startActivity(signUpIntent)

    }

    private fun firebaseSignIn(view: View, userName: String) {
        //create
        database.child("users").child(userName).get().addOnSuccessListener {
            if (it.exists()){
                //get and set all data fields from specified user
                username = it.child("userName").value as String
                userID = it.child("userID").value as String
                email = it.child("email").value as String
                userpfp = it.child("userpfp").value as String

                //toast message to display successful login
                Toast.makeText(this, "Successfully login!", Toast.LENGTH_LONG).show()
                //create intent to send user to forum page from the current page
                val mapIntent = Intent(this, MapActivity::class.java)//intent allows you to interact with other activites
                mapIntent.putExtra(EXTRA_USERNAME, username)
                mapIntent.putExtra(EXTRA_USERID, userID)
                mapIntent.putExtra(EXTRA_USEREMAIL, email)
                startActivity(mapIntent)

            }else{
                Toast.makeText(this, "User does not exist!", Toast.LENGTH_LONG).show()

            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()

        }

    }
}

