package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.ForumModel
import kotlinx.android.synthetic.main.activity_add_forum.*
import java.lang.Exception
import java.util.*

class AddForumActivity : AppCompatActivity() {
    //user variables
    var useremail = ""
    var username = ""
    var userID = ""
    //Firebase reference
    private lateinit var dbref: DatabaseReference

    //forum variables
    var forumTitle = ""
    var forumDescription = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_forum)



        //assign current user's details
        username = intent.getStringExtra(EXTRA_USERNAME).toString()
        useremail = intent.getStringExtra(EXTRA_USEREMAIL).toString()
        userID = intent.getStringExtra(EXTRA_USERID).toString()

        //database
        //dbref = FirebaseDatabase.getInstance().getReference("Forum")
        dbref = Firebase.database.reference

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
        writeNewUser(forumTitle, forumID, forumAuthor, forumDescription)

    }
    //write data to database
    fun writeNewUser(forumTitle: String, forumID: String, forumAuthor: String, forumDescription: String) {
        try{
            //ForumModel class
            val forumModel = ForumModel(forumTitle, forumID, forumAuthor, forumDescription)

            //write to firebase
            dbref.child("Forum").child(forumTitle).setValue(forumModel)
            //Toast message to inform user of success
            Toast.makeText(this, "New forum added!", Toast.LENGTH_LONG).show()

            //method to send user back to forum
            sendToForum()
        }catch (e : Exception){
            //if there is an error it will display as a toast message
            Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
        }

    }

//    //make user in UserModel
//    val user = UserModel(userName, userID, email, userpfp, password)
//    //write to firebase
//    database.child("users").child(userName).setValue(user)

    private fun sendToForum() {
        //create intent which moves to forum activity
        val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
        forumIntent.putExtra(EXTRA_USERNAME, username)
        forumIntent.putExtra(EXTRA_USERID, userID)
        forumIntent.putExtra(EXTRA_USEREMAIL, useremail)
        startActivity(forumIntent)//start activity
    }

}