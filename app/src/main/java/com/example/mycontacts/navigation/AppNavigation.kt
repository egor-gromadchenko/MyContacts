package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycontacts.ui.screens.AddEditContactScreen
import com.example.mycontacts.ui.screens.ContactsListScreen
import com.example.mycontacts.ui.screens.DetailsContactScreen
import com.example.mycontacts.ui.viewmodel.ContactsViewModel

object AppRoutes {

    const val CONTACTS_LIST = "contacts"

    const val ADD_CONTACT = "add_contact"

    const val EDIT_CONTACT =
        "add_contact/{contactId}"

    const val CONTACT_DETAILS =
        "details/{contactId}"

    fun editContact(contactId: Int): String {
        return "add_contact/$contactId"
    }

    fun contactDetails(contactId: Int): String {
        return "details/$contactId"
    }
}

@Composable
fun AppNavigation(
    viewModel: ContactsViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.CONTACTS_LIST
    ) {

        // 1. Список контактів
        composable(
            route = AppRoutes.CONTACTS_LIST
        ) {
            ContactsListScreen(
                contacts = viewModel.contacts,

                onAddClick = {
                    navController.navigate(
                        AppRoutes.ADD_CONTACT
                    )
                },

                onContactClick = { contactId ->
                    navController.navigate(
                        AppRoutes.contactDetails(
                            contactId
                        )
                    )
                }
            )
        }

        // 2. Додавання контакту
        composable(
            route = AppRoutes.ADD_CONTACT
        ) {
            AddEditContactScreen(
                contact = null,

                onSave = { name, phone, email ->
                    viewModel.addContact(
                        name = name,
                        phone = phone,
                        email = email
                    )

                    navController.popBackStack()
                },

                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        // Редагування контакту
        composable(
            route = AppRoutes.EDIT_CONTACT,
            arguments = listOf(
                navArgument("contactId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val contactId =
                backStackEntry.arguments
                    ?.getInt("contactId")

            val contact =
                contactId?.let {
                    viewModel.getContactById(it)
                }

            AddEditContactScreen(
                contact = contact,

                onSave = { name, phone, email ->

                    if (contactId != null) {
                        viewModel.updateContact(
                            id = contactId,
                            name = name,
                            phone = phone,
                            email = email
                        )
                    }

                    navController.popBackStack()
                },

                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Деталі контакту
        composable(
            route = AppRoutes.CONTACT_DETAILS,
            arguments = listOf(
                navArgument("contactId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val contactId =
                backStackEntry.arguments
                    ?.getInt("contactId")

            val contact =
                contactId?.let {
                    viewModel.getContactById(it)
                }

            DetailsContactScreen(
                contact = contact,

                onBackClick = {
                    navController.popBackStack()
                },

                onEditClick = {
                    if (contactId != null) {
                        navController.navigate(
                            AppRoutes.editContact(
                                contactId
                            )
                        )
                    }
                },

                onDeleteClick = {
                    if (contactId != null) {
                        viewModel.deleteContact(
                            contactId
                        )
                    }

                    navController.popBackStack()
                }
            )
        }
    }
}