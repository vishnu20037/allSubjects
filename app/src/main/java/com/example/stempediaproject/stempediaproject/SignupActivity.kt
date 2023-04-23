package com.example.stempediaproject.stempediaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.stempediaproject.R
import com.google.firebase.auth.FirebaseAuth



class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        val btnSignUp = findViewById<Button>(R.id.btnSign)
        btnSignUp.setOnClickListener {
            signUp()
        }
        val log = findViewById<TextView>(R.id.tv_login)
        log.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp() {
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val pass = findViewById<EditText>(R.id.etPass).text.toString()
        val cPass = findViewById<EditText>(R.id.etConfirmPass).text.toString()

        if (email.isBlank() || pass.isBlank() || cPass.isBlank()) {
            Toast.makeText(this, "Credentials can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != cPass) {
            Toast.makeText(this, "Password and Confirm Password are not same", Toast.LENGTH_SHORT)
                .show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }
}