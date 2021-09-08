package com.example.samplemvvm.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.samplemvvm.repository.LogInRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LogInViewModel @Inject constructor(var logInRepository: LogInRepository) {
    var mCurrentUser: MutableLiveData<FirebaseUser?>
    var isProcessing: MutableLiveData<Boolean> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()


    init {
        mCurrentUser = logInRepository.getUser()
        isProcessing.postValue(false)
    }

    fun logIn(view: View) {
        if (!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            isProcessing.postValue(true)
            logInRepository.logInWithEmail(email.value!!, password.value!!)
            mCurrentUser = logInRepository.getUser()
            if (mCurrentUser != null) {
                isProcessing.postValue(false)
                Snackbar.make(view, "Log In Success.", Snackbar.LENGTH_SHORT).show()
            } else {
                isProcessing.postValue(false)
                Snackbar.make(view, "Log In Failed.", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            println("fail")
            mCurrentUser.postValue(null)
            Snackbar.make(view, "Log In Failed.", Snackbar.LENGTH_SHORT).show()

        }
    }


    fun logOut() {
        println("log out")
        logInRepository.logOut()
        mCurrentUser = logInRepository.getUser()
    }


}