package es.iesra.datos

import es.iesra.dominio.Reserva
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo
import java.io.File

class DaoVuelo(): IDao<ReservaVuelo> {

    private val ficheroReservas = File("salida/reservaVuelo.txt")

    override fun salvar(reserva: ReservaVuelo): Boolean {

        try {
            ficheroReservas.appendText("${reserva.id}|${reserva.descripcion}|${reserva.origen}|${reserva.descripcion}|${reserva.horaVuelo}\n")
            return true
        } catch (e: Exception) {
            println("Error al salvar la reserva: ${e.message}")
            return false
        }
    }

    override fun eliminar(id: String): Boolean {
        try {
            val contenidoTxt = ficheroReservas.readLines()
            val contenidoMutable = contenidoTxt.toMutableList()
            contenidoMutable.removeAll { it.startsWith(id) }
            ficheroReservas.writeText(contenidoMutable.toString() + "\n")
            return true
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            return false
        }
    }

    override fun actualizar(reserva: ReservaVuelo): Boolean {
        try {
            val ficheroModificable = ficheroReservas.readLines().toMutableList()
            for (i in ficheroModificable){
                if (i.startsWith(reserva.id.toString())) {
                    val id = i[0]
                    ficheroModificable.remove(i)
                    ficheroModificable.add("${id}, ${reserva.descripcion}, ${reserva.origen}, ${reserva.destino}, ${reserva.horaVuelo}")
                }
            }
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

