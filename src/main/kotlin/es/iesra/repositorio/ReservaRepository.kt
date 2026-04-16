package es.iesra.repositorio

import es.iesra.datos.DaoFichero
import es.iesra.dominio.Reserva

/**
 * Implementación en memoria del repositorio de reservas.
 */
class ReservaRepository : IReservaRepository {
    private val dao = DaoFichero()

    override fun agregar(reserva: Reserva): Boolean {
        var agregado = dao.salvar(reserva)
        return agregado
    }

    override fun obtenerTodas(): List<String> = dao.listar()
}