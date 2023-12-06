package com.example.cryptotracker.ui.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication {
    private var auth: FirebaseAuth = Firebase.auth
    private var user: FirebaseUser

    init {
        this.user = this.auth.currentUser!!
    }

    fun getUser(): FirebaseUser {
        return this.user
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ) {
        if (isValidEmail(email) && password.isNotEmpty()) {
            this.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        this.user = this.auth.currentUser!!
                        onSuccess()
                    }
                    else onFail()
                }
        }
    }
}

val authentication: Authentication = Authentication()