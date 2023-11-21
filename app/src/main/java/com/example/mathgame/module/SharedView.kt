package com.example.mathgame.module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedView:ViewModel() {

    var person by mutableStateOf<User?>(null)

    fun addUser(newUser:User){
        person = newUser
    }

}