package com.jaredpage.playfulpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jaredpage.playfulpets.dataModels.ParkModel
import com.jaredpage.playfulpets.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        database = Firebase.database.reference
    }


    fun navigateEventAdd(view: View){
        val mainIntent = Intent(this, MainActivity::class.java)//intent allows you to interact with other activites
        startActivity(mainIntent)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        database.child("Park").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    var parkValues = postSnapshot.getValue(ParkModel::class.java)

                    mMap = googleMap

                    // Add a marker at Kenridge Dog Park and move the camera
                    val defaultPark = LatLng((parkValues?.latitude?.toDouble()!!), (parkValues.longitude?.toDouble()!!))
                    mMap.addMarker(MarkerOptions().position(defaultPark).title(parkValues.location + parkValues.parkName))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultPark))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }



        })



       /* mMap = googleMap

        // Add a marker at Kenridge Dog Park and move the camera
        val defaultPark = LatLng(-33.863337380475386, 18.633430856578908)
        mMap.addMarker(MarkerOptions().position(defaultPark).title("Kenridge Dog Park"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultPark))*/
    }
}