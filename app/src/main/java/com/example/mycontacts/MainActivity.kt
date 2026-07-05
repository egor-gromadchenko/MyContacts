package com.example.mycontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.example.mycontacts.navigation.AppNavigation
import com.example.mycontacts.ui.viewmodel.ContactsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val viewModel =
            ViewModelProvider(this)[
                ContactsViewModel::class.java
            ]

        setContent {
            MaterialTheme {
                AppNavigation(
                    viewModel = viewModel
                )
            }
        }
    }
}