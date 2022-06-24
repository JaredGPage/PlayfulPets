package com.jaredpage.playfulpets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.ParkModel
import kotlinx.android.synthetic.main.activity_add_to_map.*
import java.util.*

class AddToMap : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_map)
        database = Firebase.database.reference
    }

    //method for registering button
    fun registerSignUpBtnClicked(view: View){

        var uniqueID = UUID.randomUUID().toString()
        val park = ParkModel(ParkNameTextBox.text.toString(),
            LocationTextbox.text.toString(),
            LatitudeTextBox.text.toString(),
            LongditudeTextbox.text.toString())
        database.child("Park").child(uniqueID).setValue(park)

    }

    /*fun createNewParkEntry(parkName: String,location: String, latitude: String, longitude: String) {
        var uniqueID = UUID.randomUUID().toString()
        val park = ParkModel(parkName,location, latitude, longitude)
        database.child("Park").child(uniqueID).setValue(park)
    }*/

}