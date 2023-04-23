package com.example.stempediaproject.stempediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.stempediaproject.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null) {
            redirect("MAIN")
        }
        val btn = findViewById<Button>(R.id.btnIntro)
        btn.setOnClickListener {
            redirect("LOGIN")
        }

    }
    private fun redirect(name: String) {
        val intent = when(name) {
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw java.lang.Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }

}