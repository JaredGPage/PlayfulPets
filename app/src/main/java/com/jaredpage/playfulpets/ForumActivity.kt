package com.jaredpage.playfulpets

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forum.*

class ForumActivity : AppCompatActivity() {

    var email = ""
    var username = ""
    var userID = ""
    //Firebase user
    private lateinit var auth: FirebaseAuth;



    //method for when activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        //firebase check
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        //get current user
        val currentUser = auth.currentUser
        //assign current user's email to username
        email = currentUser?.email.toString()
        userID = currentUser?.uid.toString()
        //remove domain and check that there is a value
        val newUsername : String? = email.substringBefore("@")
        if (newUsername != null) {
            username = newUsername
        }
        //welcome text
        forumPageText.text = "Welcome $username + id = $userID"

    }


    //method for logout button
    fun forumLogoutBtnClicked(view: View){
        //firebase logout
        Firebase.auth.signOut()
        //create intent which moves to main/login activity
        val loginIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
        startActivity(loginIntent)//start activity
    }

    //method to send you to forum activity
    fun forumIntentClicked(view: View){
        //create intent which moves to forum activity
        val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
        startActivity(forumIntent)//start activity
    }

    //method to send you to maps activity
    fun mapsIntentClicked(view: View){
        //create intent which moves to maps activity
        val mapsIntent = Intent(this, EventActivity::class.java)//intent allows you to interact with other activites
        startActivity(mapsIntent)//start activity
    }

    fun insertDataBtnClicked(view: View){
        //create intent which moves to add forum activity
        val addForumIntent = Intent(this, AddForumActivity::class.java)//intent allows you to interact with other activites
        startActivity(addForumIntent)//start activity
    }
}