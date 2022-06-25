package com.jaredpage.playfulpets

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.UserModel
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_forum.*
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<UserModel>
    private lateinit var userIDList: ArrayList<UserModel>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private  lateinit var mDbRef: DatabaseReference
    var useremail = ""
    var username = ""
    var userID = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mAuth = FirebaseAuth.getInstance()
        mDbRef = Firebase.database.reference
        username = intent.getStringExtra(EXTRA_USERNAME).toString()
        useremail = intent.getStringExtra(EXTRA_USEREMAIL).toString()
        userID = intent.getStringExtra(EXTRA_USERID).toString()
        userList = ArrayList()
        userIDList = ArrayList()
        adapter = UserAdapter(this,userList, userIDList)

        userRecyclerView = findViewById(R.id.userRecyclerView)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        mDbRef.child("users").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                userIDList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(UserModel::class.java)

                    if(userID != currentUser?.userID){
                        userList.add(currentUser!!)
                    }
                    if (userID == currentUser?.userID){
                        userIDList.add(currentUser!!)
                    }
                }

                    adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun forumIntentClicked(view: View){
        //create intent which moves to forum activity
        val forumIntent = Intent(this, ForumActivity::class.java)//intent allows you to interact with other activites
        forumIntent.putExtra(EXTRA_USERNAME, username)
        forumIntent.putExtra(EXTRA_USERID, userID)
        forumIntent.putExtra(EXTRA_USEREMAIL, useremail)
        startActivity(forumIntent)//start activity
    }
    //method to send you to maps activity
    fun mapsIntentClicked(view: View){
        //create intent which moves to maps activity
        val mapsIntent = Intent(this, MapActivity::class.java)//intent allows you to interact with other activites
        startActivity(mapsIntent)//start activity
    }
    //method to send you to chat activity
    fun chatIntentClicked(view: View){
        //create intent which moves to maps activity
        val chatIntent = Intent(this, ChatActivity::class.java)//intent allows you to interact with other activites
        chatIntent.putExtra(EXTRA_USERNAME, username)
        chatIntent.putExtra(EXTRA_USERID, userID)
        chatIntent.putExtra(EXTRA_USEREMAIL, useremail)
        startActivity(chatIntent)//start activity
    }
}