package com.jaredpage.playfulpets.dataModels

data class ForumModel(val forumTitle: String?, val forumID: String? = null, val forumAuthor: String? = null, val forumDescription: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
