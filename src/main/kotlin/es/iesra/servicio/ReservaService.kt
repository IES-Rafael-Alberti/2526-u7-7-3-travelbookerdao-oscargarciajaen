package es.iesra.servicio

import es.iesra.repositorio.IReservaRepository
import es.iesra.dominio.ReservaHotel
import es.iesra.dominio.ReservaVuelo

/**
 * Implementación concreta de IReservaService.
 * Depende de la abstracción IReservaRepository, no de una implementación concreta.
 */
class ReservaService(private val repositorio: IReservaRepository) : IReservaService {

    override fun crearReservaVuelo(descripcion: String, origen: String, destino: String, horaVuelo: String) {
        val reservaVuelo = ReservaVuelo.creaInstancia(descripcion, origen, destino, horaVuelo)
        repositorio.agregar(reservaVuelo)
    }

    override fun crearReservaHotel(descripcion: String, ubicacion: String, numeroNoches: Int) {
        val reservaHotel = ReservaHotel.creaInstancia(descripcion, ubicacion, numeroNoches)
        repositorio.agregar(reservaHotel)
    }

    override fun listarReservas() = repositorio.obtenerTodas()

    override fun eliminarHotel(id: String) {
        repositorio.eliminarReservaHotel(id)
    }

    override fun eliminarVuelo(id: String) {
        repositorio.eliminarReservaVuelo(id)
    }

    override fun actualizarHotel(id: String?, descripcion: String?, ubicacion: String?, numeroNoches: Int?) {
        repositorio.actualizarHotel(id, descripcion, ubicacion, numeroNoches)
    }

    override fun actualizarVuelo(id: String?, descripcion: String?, origen: String?, destino: String?, hora: String) {
        repositorio.actualizarVuelo(id, descripcion, origen, destino, hora)
    }

    override fun obtenerVuelos(): List<String>{
        val vuelos = repositorio.obtenerVuelos()
        return vuelos
    }

    override fun obtenerHoteles(): List<String> {
        val hoteles = repositorio.obtenerHoteles()
        return hoteles
    }

}