package es.iesra.repositorio

import es.iesra.datos.DaoHotel
import es.iesra.datos.DaoVuelo
import es.iesra.dominio.Reserva
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo

/**
 * Implementación en memoria del repositorio de reservas.
 */
class ReservaRepository : IReservaRepository {
    private val daoHotel = DaoHotel()
    private val daoVuelo = DaoVuelo()

    override fun agregar(reserva: Reserva): Boolean {
        var agregado = false
        if (reserva is ReservaHotel)
            agregado = daoHotel.salvar(reserva)
        else if (reserva is ReservaVuelo)
            agregado = daoVuelo.salvar(reserva)
        return agregado
    }

    override fun obtenerVuelos(): MutableList<String>{
        val reservas = mutableListOf<String>()
        val listaVuelos = daoVuelo.listar()
        listaVuelos.forEach {reservas.add("${it.id}, ${it.descripcion}, ${it.horaVuelo}, ${it.origen}, ${it.destino}")}
        return reservas
    }

    override fun obtenerHoteles(): MutableList<String>{
        val reservas = mutableListOf<String>()
        val listaHoteles = daoHotel.listar()
        listaHoteles.forEach {reservas.add("${it.id} ${it.descripcion},${it.ubicacion},${it.numeroNoches}")}
        return reservas
    }
    override fun obtenerTodas(): List<String>{
        val reservas = mutableListOf<String>()
        val vuelos = obtenerVuelos()
        val hoteles = obtenerHoteles()
        vuelos.forEach { reservas.add(it) }
        hoteles.forEach {reservas.add(it)}
        return reservas.toList()
    }

    override fun eliminarReservaVuelo(id: String) {
        daoVuelo.eliminar(id)
    }

    fun actualizarVuelo(id: String) {
        TODO("Not yet implemented")
    }

    fun actualizarHotel(id: String) {
        TODO("Not yet implemented")
    }

    override fun eliminarReservaHotel(id: String) {
        daoHotel.eliminar(id)
    }
}