package es.iesra.datos

import es.iesra.dominio.Reserva
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo
import java.io.File

class DaoVuelo(): IDao<ReservaVuelo> {

    private val ficheroReservas = File("reservaVuelo.txt")

    override fun salvar(reserva: ReservaVuelo): Boolean {

        try {
            ficheroReservas.appendText("${reserva.id}|${reserva.descripcion}|${reserva.origen}|${reserva.descripcion}|${reserva.horaVuelo}\n")
            return true
        } catch (e: Exception) {
            println("Error al salvar la reserva: ${e.message}")
            return false
        }
    }

    override fun eliminar(reserva: ReservaVuelo): Boolean {
        try {
            val contenidoTxt = ficheroReservas.readLines()
            val contenidoMutable = contenidoTxt.toMutableList()
            contenidoMutable.removeAll { it.startsWith(reserva.id.toString()) }
            ficheroReservas.writeText(contenidoMutable.toString() + "\n")
            return true
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            return false
        }
    }

    override fun actualizar(reserva: ReservaVuelo): Boolean {
        try {
            eliminar(reserva)
            salvar(reserva)
            return true
        } catch (e: Exception) {
            println("Error al actualizar la reserva: ${e.message}")
            return false
        }
    }

    override fun listar(): List<ReservaVuelo> {
        val lista = mutableListOf<ReservaVuelo>()
        if (!ficheroReservas.exists()) return lista
        try {
            ficheroReservas.forEachLine { linea ->
                if (linea.isNotBlank()) {
                    val datos = linea.split("|")
                    lista.add(ReservaVuelo(datos[0].toInt(), datos[1],datos[2],datos[3], datos[4]))
                }
            }
        } catch (e: Exception) {
            println("Error al listar: ${e.message}")
        }
        return lista
    }
}

