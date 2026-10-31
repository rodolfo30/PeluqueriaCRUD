package com.example.peluqueriacrud.data

import com.example.peluqueriacrud.models.Cita
import java.time.LocalTime
import java.util.Calendar
import java.util.Date

fun obtenerFecha(diasDesdeHoy: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, diasDesdeHoy)
    return calendar.time
}
val listaCitas: MutableList<Cita> = mutableListOf(
    // DÍA 0 (HOY)
    Cita(obtenerFecha(0), LocalTime.of(9, 0), "Carlos Gómez", "Corte de pelo degradado"),
    Cita(obtenerFecha(0), LocalTime.of(9, 30), "María López", "Tinte completo y peinado"),
    Cita(obtenerFecha(0), LocalTime.of(10, 0), "David Martínez", "Arreglo de barba y perfilado"),
    Cita(obtenerFecha(0), LocalTime.of(10, 30), "Lucía Fernández", "Corte de puntas e hidratación"),
    Cita(obtenerFecha(0), LocalTime.of(11, 0), "Javier Rodríguez", "Corte clásico con tijera"),
    Cita(obtenerFecha(0), LocalTime.of(11, 30), "Ana Sánchez", "Mechas balayage"),
    Cita(obtenerFecha(0), LocalTime.of(12, 0), "Alejandro Pérez", "Afeitado tradicional con toalla caliente"),

    // DÍA 1 (MAÑANA)
    Cita(obtenerFecha(1), LocalTime.of(9, 0), "Elena Gómez", "Peinado para evento"),
    Cita(obtenerFecha(1), LocalTime.of(9, 45), "Pablo Martín", "Corte infantil"),
    Cita(obtenerFecha(1), LocalTime.of(10, 30), "Laura Díaz", "Tratamiento de keratina"),
    Cita(obtenerFecha(1), LocalTime.of(11, 15), "Sergio Moreno", "Corte rapado y barba"),
    Cita(obtenerFecha(1), LocalTime.of(12, 0), "Carmen Muñoz", "Lavado y peinado seco"),
    Cita(obtenerFecha(1), LocalTime.of(16, 0), "Diego Álvarez", "Diseño de cejas y barba"),
    Cita(obtenerFecha(1), LocalTime.of(17, 30), "Marta Romero", "Corte bob y tinte"),

    // DÍA 2 (PASADO MAÑANA)
    Cita(obtenerFecha(2), LocalTime.of(9, 15), "Manuel Alonso", "Corte buzz cut"),
    Cita(obtenerFecha(2), LocalTime.of(10, 0), "Paula Gutiérrez", "Recogido de fiesta"),
    Cita(obtenerFecha(2), LocalTime.of(11, 30), "Álvaro Navarro", "Corte de pelo y barba completo"),
    Cita(obtenerFecha(2), LocalTime.of(12, 15), "Sara Torres", "Mascarilla capilar intensiva"),
    Cita(obtenerFecha(2), LocalTime.of(16, 30), "Adrian Domínguez", "Corte mullet moderno"),
    Cita(obtenerFecha(2), LocalTime.of(17, 15), "Andrea Ramos", "Corte y moldeado"),
    Cita(obtenerFecha(2), LocalTime.of(18, 0), "Raúl Gil", "Perfilado de patillas y cuello"),

    // DÍA 3 (EN 3 DÍAS)
    Cita(obtenerFecha(3), LocalTime.of(9, 30), "Patricia Serrano", "Tinte de raíces"),
    Cita(obtenerFecha(3), LocalTime.of(10, 15), "Gonzalo Blanco", "Corte fade medio"),
    Cita(obtenerFecha(3), LocalTime.of(11, 0), "Rocío Molina", "Tratamiento anticaída"),
    Cita(obtenerFecha(3), LocalTime.of(12, 30), "Marcos Morales", "Corte texturizado arriba"),
    Cita(obtenerFecha(3), LocalTime.of(16, 15), "Cristina Ortega", "Corte pixie"),
    Cita(obtenerFecha(3), LocalTime.of(17, 0), "Hugo Delgado", "Afeitado de cabeza completo"),
    Cita(obtenerFecha(3), LocalTime.of(18, 30), "Beatriz Castro", "Lavado, corte y secado"),

    // DÍA 4 (EN 4 DÍAS)
    Cita(obtenerFecha(4), LocalTime.of(9, 0), "Jorge Ortiz", "Arreglo de bigote y barba"),
    Cita(obtenerFecha(4), LocalTime.of(10, 30), "Natalia Marín", "Mechas baby light"),
    Cita(obtenerFecha(4), LocalTime.of(11, 15), "Rubén Rubio", "Corte undercut"),
    Cita(obtenerFecha(4), LocalTime.of(12, 45), "Irene Núñez", "Ondas al agua"),
    Cita(obtenerFecha(4), LocalTime.of(16, 0), "Iván Medina", "Corte pompadour"),
    Cita(obtenerFecha(4), LocalTime.of(17, 45), "Silvia Castillo", "Alisado permanente"),
    Cita(obtenerFecha(4), LocalTime.of(19, 0), "Óscar Santos", "Corte ejecutivo clásico"),

    // DÍA 5 (EN 5 DÍAS)
    Cita(obtenerFecha(5), LocalTime.of(9, 15), "Monica Lozano", "Matiz de color y peinado"),
    Cita(obtenerFecha(5), LocalTime.of(10, 0), "Víctor Guerrero", "Corte mohicano sutil"),
    Cita(obtenerFecha(5), LocalTime.of(11, 30), "Alicia Cano", "Corte escalonado"),
    Cita(obtenerFecha(5), LocalTime.of(12, 15), "Guillermo Prieto", "Tinte de barba"),
    Cita(obtenerFecha(5), LocalTime.of(16, 30), "Verónica Méndez", "Peinado casual"),
    Cita(obtenerFecha(5), LocalTime.of(18, 0), "Enrique Cruz", "Corte rápido con máquina"),
    Cita(obtenerFecha(5), LocalTime.of(19, 15), "Lorena Gallego", "Corte flequillo y puntas"),

    // DÍA 6 (EN 6 DÍAS)
    Cita(obtenerFecha(6), LocalTime.of(9, 30), "Jaime Calvo", "Corte low fade"),
    Cita(obtenerFecha(6), LocalTime.of(10, 15), "Nuria Vidal", "Tratamiento de brillo (gloss)"),
    Cita(obtenerFecha(6), LocalTime.of(11, 45), "Ismael León", "Barba express"),
    Cita(obtenerFecha(6), LocalTime.of(12, 30), "Miriam Márquez", "Peinado con plancha"),

    // DÍA 7 (EN 1 SEMANA)
    Cita(obtenerFecha(7), LocalTime.of(10, 0), "Felipe Herrera", "Corte high fade"),
    Cita(obtenerFecha(7), LocalTime.of(11, 0), "Sonia Peña", "Corte capeado"),
    Cita(obtenerFecha(7), LocalTime.of(12, 0), "Tomas Flores", "Perfilado de barba"),
    Cita(obtenerFecha(7), LocalTime.of(13, 0), "Olga Cabrera", "Tratamiento detox capilar")
)

fun buscarCita(id: Int) : Cita? {
    var citaRecuperada: Cita? = null
    for (cita in listaCitas) {
        if (cita.id == id) {
            citaRecuperada = cita
        }
    }
    return citaRecuperada
}