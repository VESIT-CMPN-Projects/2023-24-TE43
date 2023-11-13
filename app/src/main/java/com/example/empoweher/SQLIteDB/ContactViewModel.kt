package com.example.empoweher.SQLIteDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {
    private val _state = MutableStateFlow(ContactState())

    fun onEvent(event: ContactEvent){
        when(event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
            ContactEvent.SaveContact -> {
                val firstName = _state.value.firstName
                val lastName = _state.value.lastName
                val phoneNumber = _state.value.phoneNumber
                val emergency = _state.value.emergency

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()){
                    return
                }

                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber,
                    emergency = emergency
                )

                viewModelScope.launch {
                    dao.insertContact(contact)
                }

                _state.update {it.copy(
                    firstName = "",
                    lastName = "",
                    phoneNumber = ""
                )

                }

            }
            is ContactEvent.SetFirstName -> _state.update {
                it.copy(
                    firstName = event.firstName
                )
            }
            is ContactEvent.SetLastName -> _state.update {
                it.copy(
                    lastName = event.lastName
                )
            }
            is ContactEvent.SetPhoneNumber -> _state.update {
                it.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            ContactEvent.hideDialog -> TODO()
            is ContactEvent.setEmergency -> _state.update {
                it.copy(
                   emergency = event.emergency
                )
            }
            ContactEvent.showDialog -> TODO()
        }
    }
}