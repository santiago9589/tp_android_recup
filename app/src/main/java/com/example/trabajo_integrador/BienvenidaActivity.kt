package com.example.trabajo_integrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Verificar si el test ya fue completado antes de mostrar la pantalla de bienvenida
        var sharedPrefs = getSharedPreferences("app_preferences", MODE_PRIVATE)
        var hasCompletedTest = sharedPrefs.getBoolean("HAS_COMPLETED_TEST", false)

        if (hasCompletedTest) {
            // Si ya completó el test, redirigir a la siguiente actividad (ej. MainActivity)
            goToMainActivity()
        } else {
            // Si no ha completado el test, mostrar la pantalla de bienvenida para que lo haga
            setContentView(R.layout.activity_bienvenida)

            var btnStartTest = findViewById<Button>(R.id.btn_StartTest)
            btnStartTest.setOnClickListener {
                startTest()
            }
        }

    }


    private fun startTest() {
        val name = findViewById<EditText>(R.id.et_Name).text.toString()
        val surname = findViewById<EditText>(R.id.et_Surname).text.toString()
        val email = findViewById<EditText>(R.id.et_Email).text.toString()

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar los datos del usuario para la siguiente actividad
        val intent = Intent(this, TestActivity::class.java).apply {
            putExtra("USER_NAME", name)
            putExtra("USER_SURNAME", surname)
            putExtra("USER_EMAIL", email)
        }
        startActivity(intent)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, ingresoDatosActivity::class.java)
        startActivity(intent)
        finish() // Finalizamos la WelcomeActivity para que no se vuelva a mostrar.
    }
}