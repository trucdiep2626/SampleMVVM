package com.example.samplemvvm.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm.repository.LogInRepository
import com.google.firebase.auth.FirebaseUser

class LogInViewModel : ViewModel {
    var mCurrentUser: MutableLiveData<FirebaseUser?>
    var logInRepository: LogInRepository

    constructor() {
        logInRepository = LogInRepository()
        mCurrentUser = logInRepository.getUser()
    }

    fun logIn(email: String, password: String, context: Context) {
        if (email != null && email != "" && password != null && password != "")
            logInRepository.logInWithEmail(email, password, context)
        else
            Toast.makeText(
                context, "Log Out Failed.",
                Toast.LENGTH_SHORT
            ).show()
    }

    fun logOut() {
        logInRepository.logOut()
    }



}