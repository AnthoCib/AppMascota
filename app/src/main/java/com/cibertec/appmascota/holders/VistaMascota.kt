package com.cibertec.appmascota.holders



import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.appmascota.R

class VistaMascota(item: View) : RecyclerView.ViewHolder(item) {

    // Atributos
    var imgFoto: ImageView
    var tvCodigo: TextView
    var tvNombre: TextView
    var tvEspecie: TextView
    var tvRaza: TextView
    var tvEdad: TextView
    var tvSexo: TextView
    var tvPeso: TextView

    // Referencias
    init {
        imgFoto = item.findViewById(R.id.imgFotoMascota)
        tvCodigo = item.findViewById(R.id.tvCodigo)
        tvNombre = item.findViewById(R.id.tvNombre)
        tvEspecie = item.findViewById(R.id.tvEspecie)
        tvRaza = item.findViewById(R.id.tvRaza)
        tvEdad = item.findViewById(R.id.tvEdad)
        tvSexo = item.findViewById(R.id.tvSexo)
        tvPeso = item.findViewById(R.id.tvPeso)
    }
}
