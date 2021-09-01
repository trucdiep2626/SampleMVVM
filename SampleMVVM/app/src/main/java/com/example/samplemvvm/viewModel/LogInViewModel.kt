package com.example.samplemvvm.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm.repository.LogInRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LogInViewModel @Inject constructor (var logInRepository: LogInRepository) {
    var mCurrentUser: MutableLiveData<FirebaseUser?>


    init {
        mCurrentUser = logInRepository.getUser()
    }

    fun logIn(email: String, password: String, context: Context) {
        if (email != null && email != "" && password != null && password != "")
            logInRepository.logInWithEmail(email, password, context)
        else {
            mCurrentUser.value=null
            Toast.makeText(
                context, "Log In Failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun logOut() {
        logInRepository.logOut()
    }



}