package com.example.mycontacts.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mycontacts.data.Contact

class ContactsViewModel : ViewModel() {

    private val _contacts = mutableStateListOf(
        Contact(
            id = 1,
            name = "Марія Сидоренко",
            phone = "+380 50 123 45 67",
            email = "maria@example.com"
        ),
        Contact(
            id = 2,
            name = "Іван Петренко",
            phone = "+380 66 987 65 43",
            email = "ivan@example.com"
        ),
        Contact(
            id = 3,
            name = "Олена Коваленко",
            phone = "+380 95 555 11 22",
            email = null
        )
    )

    val contacts: List<Contact>
        get() = _contacts

    private var nextId = 4

    fun getContactById(id: Int): Contact? {
        return _contacts.firstOrNull { it.id == id }
    }

    fun addContact(
        name: String,
        phone: String,
        email: String
    ) {
        val contact = Contact(
            id = nextId++,
            name = name.trim(),
            phone = phone.trim(),
            email = email.trim().ifBlank { null }
        )

        _contacts.add(contact)
    }

    fun updateContact(
        id: Int,
        name: String,
        phone: String,
        email: String
    ) {
        val index = _contacts.indexOfFirst { it.id == id }

        if (index == -1) {
            return
        }

        _contacts[index] = Contact(
            id = id,
            name = name.trim(),
            phone = phone.trim(),
            email = email.trim().ifBlank { null }
        )
    }

    fun deleteContact(id: Int) {
        _contacts.removeAll { it.id == id }
    }
}