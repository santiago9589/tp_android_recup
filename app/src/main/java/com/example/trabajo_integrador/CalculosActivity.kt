package com.example.trabajo_integrador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var intent = intent

        var btnRegresar = findViewById<Button>(R.id.button_volver)
        var rendimiento1 = findViewById<TextView>(R.id.res_rendimiento1)
        var rendimiento2 = findViewById<TextView>(R.id.res_rendimiento2)
        var roi1 = findViewById<TextView>(R.id.res_roi1)
        var roi2 = findViewById<TextView>(R.id.res_roi2)
        var resAnalisis = findViewById<TextView>(R.id.resultado_analisis)


        var resRendimiento1 = intent.getFloatExtra("INVERSION_REND1", 0f)
        var resRendimiento2 = intent.getFloatExtra("INVERSION_REND2", 0f)
        var resRoi1 = intent.getFloatExtra("INVERSION_ROI1", 0f)
        var resRoi2 = intent.getFloatExtra("INVERSION_ROI2", 0f)

        var resultadoText = ""

        // mostrar los resultados


        rendimiento1.text = resRendimiento1.toString()
        rendimiento2.text = resRendimiento2.toString()
        roi1.text = resRoi1.toString()
        roi2.text = resRoi2.toString()



        if (resRendimiento1 > resRendimiento2) {
            resultadoText = "La mejor inversion seria la 1"
        } else {
            resultadoText = "La mejor inversion seria la 2"
        }



        resAnalisis.text = resultadoText


        btnRegresar.setOnClickListener {

            finish()
        }


    }
}