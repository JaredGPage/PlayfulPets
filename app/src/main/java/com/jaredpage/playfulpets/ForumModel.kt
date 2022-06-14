package com.jaredpage.playfulpets

data class ForumModel(val forumID: String? = null, val forumAuthor: String? = null, val forumDescription: String? = null, val forumTitle: String?, val threadID: Int? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
