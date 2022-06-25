package com.jaredpage.playfulpets.dataModels

class UserModel {
    var userName:String? = null
    var userID: String? = null
    var email: String? = null
    var userpfp: String? = null
    var password: String? = null


    constructor(){}

    constructor(userName: String?, userID: String?, email: String?, userpfp: String?, password: String?){
        this.userName = userName
        this.userID = userName
        this.email = userName
        this.userpfp = userName
        this.password = userName

    }
}

