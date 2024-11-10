package com.example.trabajo_integrador

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var inversiones: MutableList<Inversiones> = mutableListOf()
        val preferencesServ = getSharedPreferences("INVERSIONES", Context.MODE_PRIVATE)
        var gson = Gson()
        var json = preferencesServ.getString("inversiones_data",null)
        val type = object : TypeToken<MutableList<Inversiones>>() {}.type

        var tvText = findViewById<TextView>(R.id.tv_historial)
        var btnVolver = findViewById<Button>(R.id.btn_volver_hist)
        var btnLimpiar = findViewById<Button>(R.id.btn_limpiar_hist)


        inversiones = gson.fromJson(json,type)

        if(inversiones == null){
            tvText.text = "No hay datos en la lista"
        }else{
            for ((index,inversion) in inversiones.withIndex()){
                var indexAdd = index+1
                tvText.text = tvText.text.toString() + "\n" +"Analisis "+indexAdd.toString()+"--"+"Inversion 1 " + inversion.inv1 + "--" +"Inversion 2 " + inversion.inv2 + "\n"
            }
        }

        btnVolver.setOnClickListener {
            finish()
        }


        btnLimpiar.setOnClickListener{

            var editor = preferencesServ.edit()
            var inversionesVacias = mutableListOf<Inversiones>()
            var jsonVacio = gson.toJson(inversionesVacias)
            editor.putString("inversiones_data", jsonVacio)
            editor.apply()
            finish()
        }



    }
}