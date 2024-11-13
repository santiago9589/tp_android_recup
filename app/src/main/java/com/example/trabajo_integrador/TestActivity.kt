package com.example.trabajo_integrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuramos el botón "Enviar respuestas" con un OnClickListener
        var btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            submitTest()  // Llamamos a la función submitTest() cuando se hace clic en el botón
        }
    }


    private fun submitTest() {
        var radioGroup1 = findViewById<RadioGroup>(R.id.radioGroup1)
        var selectedOptionId1 = radioGroup1.checkedRadioButtonId
        var radioGroup2 = findViewById<RadioGroup>(R.id.radioGroup1)
        var selectedOptionId2 = radioGroup1.checkedRadioButtonId
        var radioGroup3 = findViewById<RadioGroup>(R.id.radioGroup1)
        var selectedOptionId3 = radioGroup1.checkedRadioButtonId
        var radioGroup4 = findViewById<RadioGroup>(R.id.radioGroup1)
        var selectedOptionId4 = radioGroup1.checkedRadioButtonId


        if (selectedOptionId1 != -1 && selectedOptionId2 != -1 && selectedOptionId3 != -1 && selectedOptionId4 != -1) {
            var selectedOption1 = findViewById<RadioButton>(selectedOptionId1)
            var answer1 = selectedOption1.text.toString()

            var selectedOption2 = findViewById<RadioButton>(selectedOptionId2)
            var answer2 = selectedOption2.text.toString()

            var selectedOption3 = findViewById<RadioButton>(selectedOptionId3)
            var answer3 = selectedOption3.text.toString()

            var selectedOption4 = findViewById<RadioButton>(selectedOptionId4)
            var answer4 = selectedOption4.text.toString()

            // Aquí puedes almacenar la respuesta, enviarla al servidor, etc.

            // Marcamos el test como completado usando SharedPreferences
            val sharedPrefs = getSharedPreferences("app_preferences", MODE_PRIVATE)
            val editor = sharedPrefs.edit()
            editor.putBoolean("HAS_COMPLETED_TEST", true)
            editor.apply()

            // Redirigir a la pantalla principal
            val intent = Intent(this, ingresoDatosActivity::class.java)
            startActivity(intent)
            finish()  // Terminamos esta actividad para que no se pueda regresar a ella
        } else {
            // Si no se seleccionó ninguna opción, mostramos un mensaje de advertencia
            Toast.makeText(this, "Ingrese valores correctos", Toast.LENGTH_SHORT).show()
        }
    }
}