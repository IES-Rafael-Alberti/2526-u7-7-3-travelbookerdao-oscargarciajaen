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
            ficheroReservas.writeText(contenidoMutable.joinToString("\n"))
            return true
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            return false
        }
    }

    fun actualizar(id: String?, descripcion: String?, ubicacion: String?, numeroNoches: Int?): Boolean {
        try {
            val fichero = ficheroReservas.readLines()
            val ficheroModificable = ficheroReservas.readLines().toMutableList()
            for (i in fichero){
                if (id != null && i.startsWith(id)) {
                    ficheroModificable.remove(i)
                    val reservaAñadir = "${id}|${descripcion}|${ubicacion}|${numeroNoches}"
                    ficheroModificable.add(reservaAñadir)
                    ficheroReservas.writeText(ficheroModificable.toString().replace("[", "").replace("]", "") + "\n")
                    return true
                }
            }
            return false
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