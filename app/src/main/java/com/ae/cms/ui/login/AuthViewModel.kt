package com.ae.cms.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    private val _authenticatedUser = MutableLiveData<FirebaseUser?>()
    val authenticatedUser: LiveData<FirebaseUser?> = _authenticatedUser

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        _authenticatedUser.value = firebaseAuth.currentUser
    }

    fun signInUser() {
        firebaseAuth.signInWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    setAuthenticatedUser(firebaseAuth.currentUser)
                    setErrorMessage("")
                } else {
                    setErrorMessage(task.exception?.message)
                }
            }
    }

    private fun setAuthenticatedUser(currentUser: FirebaseUser?) {
        _authenticatedUser.value = currentUser
    }

    private fun setErrorMessage(error: String?) {
        _errorMessage.value = error
    }

    fun signOut() {
        firebaseAuth.signOut()
        _authenticatedUser.value = null
    }
}