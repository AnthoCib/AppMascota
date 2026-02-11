package com.cibertec.appmascota.controller

import android.content.ContentValues
import com.cibertec.appmascota.modelos.Mascota
import com.cibertec.appmascota.utils.AppConfig

class ControllerMascota {

    fun listAll(): ArrayList<Mascota> {
        val lista = ArrayList<Mascota>()

        // PASO 1: acceder a la base de datos en modo lectura
        val conn = AppConfig.BD.readableDatabase
        val rs = conn.rawQuery("select * from tb_mascota", null)

        while (rs.moveToNext()) {
            val mascota = Mascota(
                rs.getInt(0),      // cod
                rs.getString(1),   // nombre
                rs.getString(2),   // especie
                rs.getString(3),   // raza
                rs.getInt(4),      // edad
                rs.getString(5),   // sexo
                rs.getDouble(6),   // peso
                rs.getString(7)    // foto
            )
            lista.add(mascota)
        }

        rs.close()
        return lista
    }

    fun save(bean: Mascota): Int {
        var salida = -1

        val conn = AppConfig.BD.writableDatabase
        val datos = ContentValues()

        datos.put("nombre", bean.nombre)
        datos.put("especie", bean.especie)
        datos.put("raza", bean.raza)
        datos.put("edad", bean.edad)
        datos.put("sexo", bean.sexo)
        datos.put("peso", bean.peso)
        datos.put("foto", bean.foto)

        salida = conn.insert("tb_mascota", "cod", datos).toInt()

        return salida
    }

    fun findById(cod: Int): Mascota {
        lateinit var bean: Mascota

        val conn = AppConfig.BD.readableDatabase
        val rs = conn.rawQuery(
            "select * from tb_mascota where cod=?",
            arrayOf(cod.toString())
        )

        while (rs.moveToNext()) {
            bean = Mascota(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getDouble(6),
                rs.getString(7)
            )
        }

        rs.close()
        return bean
    }

    fun update(bean: Mascota): Int {
        var salida = -1

        val conn = AppConfig.BD.writableDatabase
        val datos = ContentValues()

        datos.put("nombre", bean.nombre)
        datos.put("especie", bean.especie)
        datos.put("raza", bean.raza)
        datos.put("edad", bean.edad)
        datos.put("sexo", bean.sexo)
        datos.put("peso", bean.peso)
        datos.put("foto", bean.foto)

        salida = conn.update(
            "tb_mascota",
            datos,
            "cod=?",
            arrayOf(bean.codigo.toString())
        )

        return salida
    }

    fun deletById(cod: Int): Int {
        val conn = AppConfig.BD.writableDatabase
        return conn.delete("tb_mascota", "cod=?", arrayOf(cod.toString()))
    }
}
