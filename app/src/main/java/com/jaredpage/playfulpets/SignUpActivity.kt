package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    //Firebase user
    private lateinit var auth: FirebaseAuth;
    //declare variables for user registeration
    var username = ""
    var email = ""
    var password = ""
    var checkPassword = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        //check if user is signed in (no-null) and update UI accordingly
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
    }

    //method for registering button
    fun registerSignUpBtnClicked(view: View){

        performAuth()

    }

    //method to check users entererd details
    private fun performAuth() {
        //set all the variables to their values
        username = registerEmailText.text.toString()
        email = registerEmailText.text.toString()
        password = registerPasswordText.text.toString()
        checkPassword = registerPasswordRetypeText.text.toString()

        if(!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"))){
            registerEmailText.setError("Enter correct email!")
        }else if(password.isEmpty() || password.length < 6){
            registerPasswordText.setError("Enter proper password")
        } else if(!password.equals(checkPassword)){
            registerPasswordRetypeText.setError("Passwords don't match!")
        } else{
            //call method to sign up user
            firebaseSignUp()
        }

    }

    private fun firebaseSignUp() {
        //create onCompleteListener to run once creation of user happens to then see if creation was successful
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                //create intent to move to login/main activity
                val loginIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
                startActivity(loginIntent)//start activity
            }else{
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}