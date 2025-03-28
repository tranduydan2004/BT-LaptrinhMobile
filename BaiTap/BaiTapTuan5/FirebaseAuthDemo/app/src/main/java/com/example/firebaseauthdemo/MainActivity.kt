package com.example.firebaseauthdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.firebaseauthdemo.ui.AuthScreen
import com.example.firebaseauthdemo.ui.theme.FirebaseAuthDemoTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private val authManager: AuthManager by viewModels()

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo Firebase Auth
        val auth = FirebaseAuth.getInstance()

        // Cấu hình Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Khởi tạo AuthManager
        authManager.initialize(auth)

        setContent {
            FirebaseAuthDemoTheme {
                AuthScreen(
                    authManager = authManager,
                    onSignInClick = { signInWithGoogle() }
                )
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                authManager.firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Log chi tiết lỗi để kiểm tra
                println("Google Sign-In failed with status code: ${e.statusCode}")
                when (e.statusCode) {
                    GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> {
                        authManager.updateAuthStatus(
                            "Google Sign-In Failed\nUser cancelled the Google sign-in process.",
                            false
                        )
                    }
                    GoogleSignInStatusCodes.SIGN_IN_FAILED -> {
                        authManager.updateAuthStatus(
                            "Google Sign-In Failed\nSign-in process failed.",
                            false
                        )
                    }
                    else -> {
                        authManager.updateAuthStatus(
                            "Google Sign-In Failed\nError: ${e.message}",
                            false
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authManager.checkCurrentUser()
    }
}

