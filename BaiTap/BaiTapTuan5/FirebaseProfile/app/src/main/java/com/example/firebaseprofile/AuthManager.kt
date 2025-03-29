package com.example.firebaseprofile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class AuthManager : ViewModel() {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance() // Khai báo đúng

    private lateinit var auth: FirebaseAuth

    var authStatus by mutableStateOf("")
        private set

    var isSuccess by mutableStateOf(false)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    var userName by mutableStateOf("")
        private set

    var userEmail by mutableStateOf("")
        private set

    var userPhotoUrl by mutableStateOf<String?>(null)
        private set

    var userDateOfBirth by mutableStateOf("23/05/1995") // Giá trị mặc định
        private set

    fun initialize(firebaseAuth: FirebaseAuth) {
        auth = firebaseAuth
    }

    fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            isLoggedIn = true
            userName = currentUser.displayName ?: "Unknown"
            userEmail = currentUser.email ?: "No email"
            userPhotoUrl = currentUser.photoUrl?.toString()
            // Lấy ngày sinh từ Firestore
            firestore.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        userDateOfBirth = document.getString("dateOfBirth") ?: "23/05/1995"
                    }
                }

            updateAuthStatus("Success!!\nHi ${currentUser.email}\nWelcome to UTHSmartTasks", true)
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    isLoggedIn = true
                    userName = user?.displayName ?: "Unknown"
                    userEmail = user?.email ?: "No email"
                    userPhotoUrl = user?.photoUrl?.toString()
                    // Lấy ngày sinh từ Firestore
                    if (user != null) {
                        firestore.collection("users").document(user.uid)
                            .get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    userDateOfBirth = document.getString("dateOfBirth") ?: "23/05/1995"
                                }
                            }
                    }

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

    fun updateDateOfBirth(newDate: String) {
        userDateOfBirth = newDate
        // Lưu vào Firestore
        val user = auth.currentUser
        if (user != null) {
            firestore.collection("users").document(user.uid)
                .set(mapOf("dateOfBirth" to newDate))
                .addOnSuccessListener {
                    println("Date of birth updated successfully")
                }
                .addOnFailureListener { e ->
                    println("Failed to update date of birth: $e")
                }
        }
    }

    fun logout() {
        auth.signOut()
        isLoggedIn = false
        userName = ""
        userEmail = ""
        userPhotoUrl = null
        userDateOfBirth = "23/05/1995"
        authStatus = ""
        isSuccess = false
    }
}