package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val users = mapOf(
        "jankowalski@gmail.com" to "jan123",
        "martakowalska@gmail.com" to "marta123",
        "natalkakowalska@wp.pl" to "natalka123",
        "michalkowalski@o2.pl" to "michal123"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (loginUser(email, password)) {
                // Logowanie powiodło się, przechodzimy do MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Zamykamy aktywność logowania
            } else {
                // Logowanie nie powiodło się
                Toast.makeText(this, "Błędny e-mail lub hasło", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String): Boolean {
        // Sprawdzanie czy użytkownik istnieje w bazie danych
        return users.containsKey(email) && users[email] == password
    }
}
