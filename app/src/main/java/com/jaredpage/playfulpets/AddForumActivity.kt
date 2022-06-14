package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_forum.*
import java.util.*

class AddForumActivity : AppCompatActivity() {
    //user variables
    var email = ""
    var username = ""
    var userID = ""
    //Firebase user
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    //forum variables
    var forumTitle = ""
    var forumDescription = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_forum)


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

        //database
        dbref = FirebaseDatabase.getInstance().getReference("Forum")

    }


    fun forumAddForumBtnClicked(view: View){
        saveEmployeeData()
    }

    private fun saveEmployeeData() {
        //get user inputs
        forumTitle = forumTitleText.text.toString()
        forumDescription = forumDescriptionText.text.toString()

        if (forumTitle.isEmpty()){
            forumTitleText.setError("Enter forum name!")
        }
        if(forumDescription.isEmpty()){
            forumDescriptionText.setError("Please enter description")
        }

        //create uniques id
        var uniqueID = UUID.randomUUID().toString()

        //assign data used to insert into firabase database from user inputed data
        val forumAuthor = userID
        val forumDescription = forumDescription
        val forumID = uniqueID
        val forumTitle = forumTitle
        val threadID = 2
        writeNewUser(forumID, forumAuthor, forumDescription, forumTitle, threadID)

    }
    //write data to database
    fun writeNewUser(forumID: String, forumAuthor: String, forumDescription: String, forumTitle: String, threadID: Int) {
        val forumModel = ForumModel(forumID, forumAuthor, forumDescription, forumTitle, threadID)

        //pathstring is the name of the forum
        dbref.child(forumTitle).child(forumID.toString()).setValue(forumModel)

        //method to send user back to forum
        sendToForum()
    }

    private fun sendToForum() {
        //create intent which moves to forum activity
        val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
        startActivity(forumIntent)//start activity
    }

}