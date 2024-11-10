package com.example.trabajo_integrador

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class ingresoDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ingreso_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val inversiones: MutableList<Inversiones> = mutableListOf()


        // shared preferences

        val preferencesServ = getSharedPreferences("INVERSIONES", Context.MODE_PRIVATE)


        // spinners configuracion

        val tipoDatos = arrayOf("PLAZO FIJO", "FCI") // Usamos `val` en vez de `var`
        val spinner1 = findViewById<Spinner>(R.id.inversion_1_op)
        val spinner2 = findViewById<Spinner>(R.id.inversion_2_op)

        // No es necesario especificar el tipo de ArrayAdapter aquí, ya que Kotlin lo infiere
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tipoDatos
        )

        // Establecemos el adaptador al Spinner
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        // spinners configuracion


        // botones

        var btnCalcular = findViewById<Button>(R.id.btn_calcular)
        var btnHistorial = findViewById<Button>(R.id.btn_historial)
        var btnPermisos = findViewById<Button>(R.id.btn_permisos)


        var editor = preferencesServ.edit()
        val gson = Gson()
        var json = gson.toJson(inversiones)
        editor.putString("inversiones_data",json)
        editor.apply()



        btnCalcular.setOnClickListener {


            // inversion 1

            var etmonto1 = findViewById<EditText>(R.id.monto_1)
            var monto1 = etmonto1.text.toString()
            var ettasa1 = findViewById<EditText>(R.id.tasa_1)
            var tasa1 = ettasa1.text.toString()
            var etentidad1 = findViewById<EditText>(R.id.entidad_1)
            var entidad1 = etentidad1.text.toString()
            var etplazo1 = findViewById<EditText>(R.id.plazo_1)
            var plazo1 = etplazo1.text.toString()
            var rendimiento1 = 0f
            var Roi1 = 0f

            // inversion 2

            var etmonto2 = findViewById<EditText>(R.id.monto_2)
            var monto2 = etmonto2.text.toString()
            var ettasa2 = findViewById<EditText>(R.id.tasa_2)
            var tasa2 = ettasa2.text.toString()
            var etentidad2 = findViewById<EditText>(R.id.entidad_2)
            var entidad2 = etentidad2.text.toString()
            var etplazo2 = findViewById<EditText>(R.id.plazo_2)
            var plazo2 = etplazo2.text.toString()
            var rendimiento2 = 0f
            var Roi2 = 0f


            // calculos


            if (monto1.isEmpty() || monto2.isEmpty() || tasa1.isEmpty() || tasa2.isEmpty() ||
                entidad1.isEmpty() || entidad2.isEmpty() || plazo1.isEmpty() || plazo2.isEmpty()
            ) {
                Toast.makeText(this, "Ingreso de datos es invalido", Toast.LENGTH_SHORT).show()
            } else {
                var tasaParseada1 = (tasa1.toFloat() / 360) / 100
                rendimiento1 =
                    (monto1.toFloat()) * (tasaParseada1) * (plazo1.toFloat())
                var montoFinal1 = monto1.toFloat() + rendimiento1
                Roi1 = ((montoFinal1 - monto1.toFloat()) / monto1.toFloat()) * 100

                var tasaParseada2 = (tasa2.toFloat() / 360) / 100
                rendimiento2 =
                    (monto2.toFloat()) * (tasaParseada2) * (plazo2.toFloat())
                var montoFinal2 = monto2.toFloat() + rendimiento2
                Roi2 = ((montoFinal2 - monto2.toFloat()) / monto2.toFloat()) * 100




                inversiones.add(Inversiones(Roi1.toString(),Roi2.toString()))
                json = gson.toJson(inversiones)
                editor.putString("inversiones_data",json)
                editor.apply()


                // configurar intent

                var intent = Intent(this, CalculosActivity::class.java)
                intent.putExtra("INVERSION_REND1", rendimiento1)
                intent.putExtra("INVERSION_REND2", rendimiento2)
                intent.putExtra("INVERSION_ROI1", Roi1)
                intent.putExtra("INVERSION_ROI2", Roi2)
                startActivity(intent)



                etmonto1.text.clear()

                ettasa1.text.clear()

                etentidad1.text.clear()

                etplazo1.text.clear()


                etmonto2.text.clear()

                ettasa2.text.clear()

                etentidad2.text.clear()

                etplazo2.text.clear()



            }






        }


        btnPermisos.setOnClickListener {
            var intent = Intent(this, PoliticaActivity::class.java)
            startActivity(intent)

        }


        btnHistorial.setOnClickListener {
            var intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)

        }

    }


}

















