package com.cibertec.appmascota.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.appmascota.DatosMascotaMainActivity
import com.cibertec.appmascota.R
import com.cibertec.appmascota.holders.VistaMascota
import com.cibertec.appmascota.modelos.Mascota
import com.cibertec.appmascota.utils.AppConfig

class MascotaAdapter(var data: ArrayList<Mascota>) :
    RecyclerView.Adapter<VistaMascota>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaMascota {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mascota, parent, false)
        return VistaMascota(item)
    }

    override fun onBindViewHolder(holder: VistaMascota, position: Int) {

        val mascota = data[position]

        // Mostrar datos
        holder.tvCodigo.text = mascota.codigo.toString()
        holder.tvNombre.text = mascota.nombre
        holder.tvEspecie.text = mascota.especie
        holder.tvRaza.text = mascota.raza
        holder.tvEdad.text = mascota.edad.toString()
        holder.tvSexo.text = mascota.sexo
        holder.tvPeso.text = mascota.peso.toString()

        // Foto (desde drawable)
        val resId = holder.itemView.context.resources
            .getIdentifier(mascota.foto, "drawable", holder.itemView.context.packageName)

        if (resId != 0) {
            holder.imgFoto.setImageResource(resId)
        } else {
            holder.imgFoto.setImageResource(R.drawable.ic_launcher_foreground)
        }

        // Evento click → Detalle / Editar
        holder.itemView.setOnClickListener {
            val intent = Intent(AppConfig.CONTEXTO, DatosMascotaMainActivity::class.java)
            intent.putExtra("codigo", mascota.codigo)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }}