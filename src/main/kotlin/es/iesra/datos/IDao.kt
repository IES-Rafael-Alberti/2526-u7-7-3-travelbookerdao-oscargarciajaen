package es.iesra.datos

import es.iesra.dominio.Reserva

interface IDao<T> {

    fun salvar(reserva: T): Boolean

    fun eliminar(id: String): Boolean

    fun actualizar(reserva: T): Boolean

    fun listar(): List<Reserva>
}