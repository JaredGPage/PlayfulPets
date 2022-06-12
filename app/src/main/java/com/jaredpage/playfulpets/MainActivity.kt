package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jaredpage.playfulpets.User.EXTRA_USER
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //declare variables for user login
    var username = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //method for when login button clicked
    fun loginLoginBtnClicked(view: View){
        //assign variables values from UI
        username = loginUsernameText.text.toString()
        password = loginPasswordText.text.toString()

        //create intent which moves to event/maps activity
        val eventIntent = Intent(this, EventActivity::class.java)//intent allows you to interact with other activites
        eventIntent.putExtra(EXTRA_USER, username)//parse username to next activity, stored in userInformation.kt
        startActivity(eventIntent)//start activity
    }

    //method for when sign up button clicked
    fun loginRegisterBtnClicked(view: View){

        val signUpIntent = Intent(this, SignUpActivity::class.java)//intent allows you to interact with other activites
        startActivity(signUpIntent)

    }
}