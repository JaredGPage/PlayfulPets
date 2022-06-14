package com.jaredpage.playfulpets

data class UserModel(val userName: String? = null, val email: String? = null, val userpfp: String?, val password: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

