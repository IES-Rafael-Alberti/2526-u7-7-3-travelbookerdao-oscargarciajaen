package es.iesra.repositorio

import es.iesra.dominio.Reserva

/**
 * Interfaz que define las operaciones básicas para almacenar y recuperar reservas.
 */
interface IReservaRepository {
    fun agregar(reserva: Reserva): Boolean
    fun obtenerTodas(): List<String>
    fun obtenerHoteles(): MutableList<String>
    fun obtenerVuelos():MutableList<String>
    fun eliminarReservaHotel(id: String)
    fun eliminarReservaVuelo(id: String)
    fun actualizarVuelo()
    fun actualizarHotel()
}
