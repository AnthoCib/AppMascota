package com.cibertec.appmascota

import android.content.DialogInterface
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

class DatosMascotaMainActivity: AppCompatActivity() {
    private lateinit var txtCodigo: TextInputEditText
    private lateinit var txtNombre: TextInputEditText
    private lateinit var txtEspecie: TextInputEditText
    private lateinit var txtRaza: TextInputEditText
    private lateinit var txtEdad: TextInputEditText
    private lateinit var txtPeso: TextInputEditText
    private lateinit var atvSexo: AutoCompleteTextView

    private lateinit var btnModificar: Button
    private lateinit var btnVolver: Button
    private lateinit var btnEliminar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.datos_mascota_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias
        txtCodigo = findViewById(R.id.txtCodigoMascota)
        txtNombre = findViewById(R.id.txtNombreMascota)
        txtEspecie = findViewById(R.id.txtEspecieMascota)
        txtRaza = findViewById(R.id.txtRazaMascota)
        txtEdad = findViewById(R.id.txtEdadMascota)
        txtPeso = findViewById(R.id.txtPesoMascota)
        atvSexo = findViewById(R.id.atvSexoMascota)

        btnModificar = findViewById(R.id.btnModificarMascota)
        btnVolver = findViewById(R.id.btnVolverMascota)
        btnEliminar = findViewById(R.id.btnEliminarMascota)

        // Recuperar código enviado desde el RecyclerView
        val codigo = intent.getIntExtra("codigo", -1)
        if (codigo == -1) {
            showAlert("No se recibieron datos de la mascota")
            finish()
            return
        }
        buscar(codigo)

        btnModificar.setOnClickListener {
            val cod = txtCodigo.text.toString().toIntOrNull()
            val nom = txtNombre.text.toString()
            val esp = txtEspecie.text.toString()
            val raza = txtRaza.text.toString()
            val edad = txtEdad.text.toString().toIntOrNull()
            val sexo = atvSexo.text.toString()
            val peso = txtPeso.text.toString().toDoubleOrNull()

            if (cod == null || edad == null || peso == null) {
                showAlert("Código, edad y peso deben ser numéricos válidos")
                return@setOnClickListener
            }

            if (nom.isBlank() || esp.isBlank() || raza.isBlank() || sexo.isBlank()) {
                showAlert("Completa todos los campos antes de modificar")
                return@setOnClickListener
            }

            val bean = Mascota(cod, nom, esp, raza, edad, sexo, peso, "")
            val salida = ControllerMascota().update(bean)

            if (salida > 0)
                showAlert("Mascota actualizada")
            else
                showAlert("Error en la actualización")
        }

        btnVolver.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnEliminar.setOnClickListener {
            showAlertConfirm("¿Eliminar mascota?")
        }
    }

    private fun buscar(cod: Int) {
        val bean = ControllerMascota().findById(cod)

        if (bean == null) {
            showAlert("Mascota no encontrada")
            finish()
            return
        }

        txtCodigo.setText(bean.codigo.toString())
        txtNombre.setText(bean.nombre)
        txtEspecie.setText(bean.especie)
        txtRaza.setText(bean.raza)
        txtEdad.setText(bean.edad.toString())
        atvSexo.setText(bean.sexo, false)
        txtPeso.setText(bean.peso.toString())
    }

    private fun showAlert(men: String) {
        AlertDialog.Builder(this)
            .setTitle("SISTEMA")
            .setMessage(men)
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }

    private fun showAlertConfirm(men: String) {
        AlertDialog.Builder(this)
            .setTitle("SISTEMA")
            .setMessage(men)
            .setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
                val cod = txtCodigo.text.toString().toIntOrNull()
                if (cod == null) {
                    showAlert("Código inválido")
                    return@setPositiveButton
                }

                val salida = ControllerMascota().deletById(cod)

                if (salida > 0)
                    showAlert("Mascota eliminada")
                else
                    showAlert("Error al eliminar")
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}