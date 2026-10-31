package com.example.peluqueriacrud.models

import java.time.LocalTime
import java.util.Date

data class Cita (
    var fecha : Date,
    var hora : LocalTime,
    var nombre : String,
    var descripcion : String
){
    companion object {
        var contador = 0
        fun siguienteID(): Int = ++contador
    }
    val id: Int = siguienteID()
}