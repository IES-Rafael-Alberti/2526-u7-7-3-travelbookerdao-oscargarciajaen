package es.iesra.datos

import es.iesra.dominio.ReservaHotel
import java.io.File

class DaoHotel() : IDao<ReservaHotel> {

    private val ficheroReservas = File("salida/reservaHotel.txt")

    override fun salvar(reserva: ReservaHotel): Boolean {

        try {
            var contador = 1
            for (i in ficheroReservas.readLines()){
                contador += 1
            }
            ficheroReservas.appendText("${contador}|${reserva.descripcion}|${reserva.ubicacion}|${reserva.numeroNoches}\n")
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

    override fun actualizar(reserva: ReservaHotel): Boolean {
        try {
            val ficheroModificable = ficheroReservas.readLines().toMutableList()
            for (i in ficheroModificable){
                if (i.startsWith(reserva.id.toString())) {
                    val id = i[0]
                    ficheroModificable.remove(i)
                    ficheroModificable.add("${id}, ${reserva.descripcion}, ${reserva.ubicacion}, ${reserva.numeroNoches}")
                }
            }
            return true
        } catch (e: Exception) {
            println("Error al actualizar la reserva: ${e.message}")
            return false
        }
    }

    override fun listar(): List<ReservaHotel> {
        val lista = mutableListOf<ReservaHotel>()
        if (!ficheroReservas.exists()) return lista
        try {
            ficheroReservas.forEachLine { linea ->
                if (linea.isNotBlank()) {
                    val datos = linea.split("|")
                    lista.add(ReservaHotel(datos[0].toInt(),datos[1],datos[2], datos[3].toInt()))
                }
            }
        } catch (e: Exception) {
            println("Error al listar: ${e.message}")
        }
        return lista
    }
}