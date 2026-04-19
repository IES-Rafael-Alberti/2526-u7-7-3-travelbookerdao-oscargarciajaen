package es.iesra.datos

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
            ficheroReservas.writeText(contenidoMutable.joinToString("\n"))
            return true
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            return false
        }
    }

    fun actualizar(id : String?, descripcion: String?, origen: String?, destino: String?, hora: String): Boolean {
        try {
            val fichero = ficheroReservas.readLines()
            val ficheroModificable = ficheroReservas.readLines().toMutableList()
            for (i in fichero){
                if (id != null && i.startsWith(id)) {
                    ficheroModificable.remove(i)
                    val reservaAñadir =  "${id}, ${descripcion}, ${origen}, ${destino}, ${hora}"
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

