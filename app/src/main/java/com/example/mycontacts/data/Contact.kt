package com.example.mycontacts.data

data class Contact(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String? = null
)