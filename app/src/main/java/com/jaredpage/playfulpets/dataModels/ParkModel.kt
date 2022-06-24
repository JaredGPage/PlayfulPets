package com.jaredpage.playfulpets.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ParkModel(val parkName: String? = null, val location: String? = null, val latitude: String? = null, val longitude: String? = null) {

}