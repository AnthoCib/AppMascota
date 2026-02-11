package com.cibertec.appmascota

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.appmascota.controller.ControllerMascota
import com.cibertec.appmascota.modelos.Mascota
import com.google.android.material.textfield.TextInputEditText

class NuevaMascotaMainActivity : AppCompatActivity(){
    private lateinit var txtNombre: TextInputEditText
    private lateinit var txtEspecie: TextInputEditText
    private lateinit var txtRaza: TextInputEditText
    private lateinit var txtEdad: TextInputEditText
    private lateinit var txtPeso: TextInputEditText
    private lateinit var atvSexo: AutoCompleteTextView
    private lateinit var btnGrabar: Button
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.nueva_mascota_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias
        txtNombre = findViewById(R.id.txtNombre)
        txtEspecie = findViewById(R.id.txtEspecie)
        txtRaza = findViewById(R.id.txtRaza)
        txtEdad = findViewById(R.id.txtEdad)
        txtPeso = findViewById(R.id.txtPeso)
        atvSexo = findViewById(R.id.atvSexo)
        btnGrabar = findViewById(R.id.btnGuardar)
        btnVolver = findViewById(R.id.btnCerrar)

        btnGrabar.setOnClickListener {

            val nombre = txtNombre.text.toString()
            val especie = txtEspecie.text.toString()
            val raza = txtRaza.text.toString()
            val edad = txtEdad.text.toString().toInt()
            val peso = txtPeso.text.toString().toDouble()
            val sexo = atvSexo.text.toString()
            val foto = ""

            val mascota = Mascota(
                0,
                nombre,
                especie,
                raza,
                edad,
                sexo,
                peso,
                foto
            )

            val resu = ControllerMascota().save(mascota)

            if (resu > 0)
                showAlert("Mascota registrada correctamente 🐶")
            else
                showAlert("Error en el registro de la mascota")
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun showAlert(men: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}