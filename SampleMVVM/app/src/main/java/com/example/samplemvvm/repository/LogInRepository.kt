package com.example.samplemvvm.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class LogInRepository @Inject constructor(val _fireBaseAuth: FirebaseAuth) : ILogInRepository {
    private var _mCurrentUser: MutableLiveData<FirebaseUser?> = MutableLiveData()

    override fun logInWithEmail(email: String, password: String, ) {
        _fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _mCurrentUser.postValue(_fireBaseAuth.currentUser)
//                Toast.makeText(
//                    context, "Log In Success",
//                    Toast.LENGTH_SHORT
//                ).show()
            } else {
                _mCurrentUser.postValue(null)
//                Toast.makeText(
//                    context, "Log In Failed.",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }
    }

    override fun logOut() {
        _fireBaseAuth.signOut()
        println("log out"+ _fireBaseAuth.currentUser)
        _mCurrentUser.postValue(null)
    }

    override fun getUser(): MutableLiveData<FirebaseUser?> = _mCurrentUser

}