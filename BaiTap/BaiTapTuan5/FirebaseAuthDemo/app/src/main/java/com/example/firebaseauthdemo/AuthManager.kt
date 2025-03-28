package com.example.firebaseauthdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthManager : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var authStatus by mutableStateOf("")
        private set

    var isSuccess by mutableStateOf(false)
        private set

    fun initialize(firebaseAuth: FirebaseAuth) {
        auth = firebaseAuth
    }

    fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateAuthStatus("Success!!\nHi ${currentUser.email}\nWelcome to UTHSmartTasks", true)
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateAuthStatus("Success!!\nHi ${user?.email}\nWelcome to UTHSmartTasks", true)
                } else {
                    updateAuthStatus("Google Sign-In Failed\nAuthentication failed.", false)
                }
            }
    }

    fun updateAuthStatus(status: String, success: Boolean) {
        authStatus = status
        isSuccess = success
    }
}