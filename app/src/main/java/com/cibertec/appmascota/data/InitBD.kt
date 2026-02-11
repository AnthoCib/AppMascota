package com.cibertec.appmascota.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cibertec.appmascota.utils.AppConfig

class InitBD : SQLiteOpenHelper(
    AppConfig.CONTEXTO,
    "mascotas.bd",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase) {

        // Crear tabla tb_mascota
        db.execSQL(
            "create table tb_mascota (" +
                    "cod integer primary key autoincrement," +
                    "nombre varchar(30)," +
                    "especie varchar(20)," +
                    "raza varchar(30)," +
                    "edad int," +
                    "sexo varchar(15)," +
                    "peso double" +
                    ")"
        )

        // Datos de prueba
        db.execSQL("insert into tb_mascota values(null,'Firulais','Perro','Labrador',3,'Macho',25.5)")
        db.execSQL("insert into tb_mascota values(null,'Michi','Gato','Siames',2,'Hembra',4.2)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Aquí irían cambios de versión de la BD
    }

}