package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    //method for registering button
    fun registerSignUpBtnClicked(view: View){


        val loginIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
        startActivity(loginIntent)
    }
}