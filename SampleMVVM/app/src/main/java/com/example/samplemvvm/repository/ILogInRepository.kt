package com.example.samplemvvm.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface ILogInRepository {

    fun logInWithEmail(email: String, password: String, context: Context)

    fun logOut()

    fun getUser(): MutableLiveData<FirebaseUser?>
}