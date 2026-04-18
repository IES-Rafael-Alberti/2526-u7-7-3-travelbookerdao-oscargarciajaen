package es.iesra.servicio

import es.iesra.dominio.Reserva


/**
 * Interfaz que define los servicios disponibles para gestionar reservas.
 * Se aplica la inversión de dependencias en la capa de presentación.
 */
interface IReservaService {
    fun crearReservaVuelo(descripcion: String, origen: String, destino: String, horaVuelo: String)
    fun crearReservaHotel(descripcion: String, ubicacion: String, numeroNoches: Int)
    fun listarReservas(): List<String>
    fun eliminarHotel(id: String)
    fun eliminarVuelo(id: String)
    fun actualizarHotel(id: String)
    fun actualizarVuelo(id: String)
    fun obtenerVuelos(): List<String>
    fun obtenerHoteles(): List<String>
}
