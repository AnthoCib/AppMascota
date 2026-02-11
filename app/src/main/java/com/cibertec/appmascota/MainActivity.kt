package com.cibertec.appmascota

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.appmascota.adaptador.MascotaAdapter
import com.cibertec.appmascota.controller.ControllerMascota


class MainActivity : AppCompatActivity() {

    private lateinit var btnNuevo: Button
    private lateinit var rvMascotas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNuevo = findViewById(R.id.btnNuevaMascota)
        rvMascotas = findViewById(R.id.rvMascotas)

        // Adapter con datos desde SQLite
        val adaptador = MascotaAdapter(ControllerMascota().listAll())
        rvMascotas.adapter = adaptador
        rvMascotas.layoutManager = LinearLayoutManager(this)

        btnNuevo.setOnClickListener {
            val intent = Intent(this, NuevaMascotaMainActivity::class.java)
            startActivity(intent)
        }
    }
}
