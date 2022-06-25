package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.internal.BackgroundDetector.initialize
import com.google.android.gms.common.api.internal.GoogleServices.initialize
import com.google.android.gms.maps.MapsInitializer.initialize
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.UserModel
import java.util.*

class PrivateChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: Button
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var adapter: UserAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var userList: ArrayList<UserModel>
    private lateinit var userIDList: ArrayList<UserModel>
    private lateinit var mDbRef: DatabaseReference


    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_chat)


        val name = intent.getStringExtra("username")
        val receiverUid = intent.getStringExtra("userID")
        val senderUid = intent.getStringExtra("userID2")

        mDbRef = FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUid + senderUid

        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sentButton)
        messageList = ArrayList()
        userList = ArrayList()
        userIDList = ArrayList()
        adapter = UserAdapter(this,userList, userIDList)
        messageAdapter = MessageAdapter(this, messageList, userIDList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        //logic for adding data to recycler view
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()

                    for(postSnapshot in snapshot.children){

                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)

                    }
                    messageAdapter.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        mDbRef.child("users").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userIDList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(UserModel::class.java)

                    if(senderUid != currentUser?.userID){
                        userList.add(currentUser!!)
                    }
                    if (senderUid == currentUser?.userID){
                        userIDList.add(currentUser!!)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


        //adding message to database
        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            val messageObject = Message(message,senderUid)

            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")
        }
    }
}