package es.iesra.repositorio

import es.iesra.dominio.Reserva

/**
 * Interfaz que define las operaciones básicas para almacenar y recuperar reservas.
 */
interface IReservaRepository {
    fun agregar(reserva: Reserva): Boolean
    fun obtenerTodas(): List<String>
}
