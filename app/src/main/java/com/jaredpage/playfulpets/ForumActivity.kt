package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forum.*

class ForumActivity : AppCompatActivity() {

    var useremail = ""
    var username = ""
    var userID = ""
    //Firebase reference
    private lateinit var dbref: DatabaseReference



    //method for when activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        //database
        //dbref = FirebaseDatabase.getInstance().getReference("Forum")
        dbref = Firebase.database.reference

        //assign current user's details
        username = intent.getStringExtra(EXTRA_USERNAME).toString()
        useremail = intent.getStringExtra(EXTRA_USEREMAIL).toString()
        userID = intent.getStringExtra(EXTRA_USERID).toString()


        //welcome text
        forumPageText.text = "Welcome $username + id = $userID + email = $useremail"

    }


    //method for logout button
    fun forumLogoutBtnClicked(view: View){

        //create intent which moves to main/login activity
        val loginIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
        startActivity(loginIntent)//start activity
        //set any current values to null
        // TODO
        //
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
        val mapsIntent = Intent(this, MapActivity::class.java)//intent allows you to interact with other activites
        startActivity(mapsIntent)//start activity
    }

    fun insertDataBtnClicked(view: View){
        //create intent which moves to add forum activity
        val addForumIntent = Intent(this, AddForumActivity::class.java)//intent allows you to interact with other activites
        addForumIntent.putExtra(EXTRA_USERNAME, username)
        addForumIntent.putExtra(EXTRA_USERID, userID)
        addForumIntent.putExtra(EXTRA_USEREMAIL, useremail)
        startActivity(addForumIntent)//start activity
    }
}