package com.example.samplemvvm.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface ILogInRepository {

    fun logInWithEmail(email: String, password: String,  )

    fun logOut()

    fun getUser(): MutableLiveData<FirebaseUser?>
}