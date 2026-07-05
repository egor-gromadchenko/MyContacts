package com.example.mycontacts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mycontacts.data.Contact
import com.example.mycontacts.ui.components.AppHeader
import com.example.mycontacts.ui.theme.ContactAccent
import com.example.mycontacts.ui.theme.ContactBackground

@Composable
fun AddEditContactScreen(
    contact: Contact?,
    onSave: (
        name: String,
        phone: String,
        email: String
    ) -> Unit,
    onCancel: () -> Unit
) {
    var name by rememberSaveable(contact?.id) {
        mutableStateOf(contact?.name.orEmpty())
    }

    var phone by rememberSaveable(contact?.id) {
        mutableStateOf(contact?.phone.orEmpty())
    }

    var email by rememberSaveable(contact?.id) {
        mutableStateOf(contact?.email.orEmpty())
    }

    val isEditing = contact != null

    Scaffold(
        containerColor = ContactBackground,
        topBar = {
            AppHeader(
                title = if (isEditing) {
                    "Редагування"
                } else {
                    "Новий контакт"
                },
                onBackClick = onCancel
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Ім'я")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = {
                    Text("Телефон")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text("Email (необов'язково)")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onSave(
                        name,
                        phone,
                        email
                    )
                },
                enabled = name.isNotBlank() &&
                        phone.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ContactAccent
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isEditing) {
                        "Зберегти зміни"
                    } else {
                        "Зберегти"
                    }
                )
            }

            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Скасувати")
            }
        }
    }
}