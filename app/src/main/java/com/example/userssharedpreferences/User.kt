package com.example.userssharedpreferences

data class User(val id: Long, val name: String, val lastName: String, val urlImage: String) {

    fun getFullName(): String = "$name $lastName"
}