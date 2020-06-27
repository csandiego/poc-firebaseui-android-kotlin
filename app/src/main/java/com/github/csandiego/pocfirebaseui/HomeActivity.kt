package com.github.csandiego.pocfirebaseui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private val listener = FirebaseAuth.AuthStateListener { auth ->
        if (auth.currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            AuthUI.getInstance().signOut(this)
        }
    }
    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().addAuthStateListener(listener)
    }

    override fun onPause() {
        super.onPause()
        FirebaseAuth.getInstance().removeAuthStateListener(listener)
    }

}