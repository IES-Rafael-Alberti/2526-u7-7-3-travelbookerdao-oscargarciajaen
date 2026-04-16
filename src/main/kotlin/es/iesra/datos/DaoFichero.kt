package es.iesra.datos

import es.iesra.dominio.Reserva
import java.io.File

class DaoFichero() : IDao<Reserva> {

    private val ficheroReservas = File("reserva.txt")

    override fun salvar(reserva: Reserva): Boolean {

        try {
            ficheroReservas.appendText(reserva.toString() + "\n")
            return true
        } catch (e: Exception) {
            println("Error al salvar la reserva: ${e.message}")
            return false
        }
    }

    override fun eliminar(reserva: Reserva): Boolean {
        try {
            val contenidoTxt = ficheroReservas.readLines()
            val contenidoMutable = contenidoTxt.toMutableList()
            contenidoMutable.removeAll { it.contains(reserva.toString()) }
            ficheroReservas.writeText(contenidoMutable.toString() + "\n")
            return true
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            return false
        }
    }

    override fun actualizar(reserva: Reserva): Boolean {
        try {
            val contenidoTxt = ficheroReservas.readLines()
            val contenidoMutable = contenidoTxt.toMutableList()
            contenidoMutable.removeAll { it.startsWith("${reserva.id} - ") }
            contenidoMutable.add(reserva.toString())
            ficheroReservas.writeText(contenidoMutable.joinToString("\n") + "\n")
            return true
        } catch (e: Exception) {
            println("Error al actualizar la reserva: ${e.message}")
            return false
        }
    }

    override fun listar(): List<String> {
        var listaReservas: List<String> = listOf()
        try {
            listaReservas = ficheroReservas.readLines()
        }catch (e:Exception){
            println("Error al listar la reserva: ${e.message}")
        }
        return listaReservas
    }

}