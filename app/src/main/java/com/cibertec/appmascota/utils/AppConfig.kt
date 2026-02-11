package com.cibertec.appmascota.utils

import android.app.Application
import android.content.Context
import com.cibertec.appmascota.data.InitBD

class AppConfig: Application() {

    companion object{
        lateinit var CONTEXTO: Context
        lateinit var BD: InitBD

    }
    //inicializar las variables globales
    override fun onCreate() {
        CONTEXTO=applicationContext
        BD= InitBD()
        super.onCreate()
    }
}