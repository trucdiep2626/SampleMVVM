package com.example.samplemvvm.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInRepository : ILogInRepository {
    private lateinit var _fireBaseAuth: FirebaseAuth
    private lateinit var _mCurrentUser: MutableLiveData<FirebaseUser?>

    constructor(){
        _fireBaseAuth = FirebaseAuth.getInstance()
        _mCurrentUser = MutableLiveData()
    }

    override fun logInWithEmail(email: String, password: String, context: Context) {
        _fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _mCurrentUser.postValue(_fireBaseAuth.currentUser)
                Toast.makeText(
                    context, "Log In Successed.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                _mCurrentUser.value=null
                Toast.makeText(
                    context, "Log In Failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun logOut() {
        _fireBaseAuth.signOut()
        _mCurrentUser.value=null
    }

    override fun getUser():MutableLiveData<FirebaseUser?> = _mCurrentUser

}