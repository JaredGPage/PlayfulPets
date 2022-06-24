package com.jaredpage.playfulpets.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class EventModel(val EventId: String? = null,
                      val eventName: String? = null,
                      val eventDescription: String? = null,
                      val eventTime: String? = null,
                      val eventAuthor: String? = null,
                      val eventDetails: String? = null,
                      val parkId: String? = null) {
}