package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    //declare variables for user registeration
    var username = ""
    var email = ""
    var password = ""
    var checkPassword = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    //method for registering button
    fun registerSignUpBtnClicked(view: View){

        username = registerUsernameText.text.toString()
        email = registerEmailText.text.toString()
        password = registerPasswordText.text.toString()
        checkPassword = registerPasswordRetypeText.text.toString()

        Toast.makeText(this, "$username and $password and $email and $checkPassword", Toast.LENGTH_SHORT).show()


        val loginIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
        startActivity(loginIntent)
    }
}