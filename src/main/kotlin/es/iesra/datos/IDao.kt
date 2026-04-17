package es.iesra.datos

import es.iesra.dominio.Reserva

interface IDao<T> {

    fun salvar(reserva: T): Boolean

    fun eliminar(reserva: T): Boolean

    fun actualizar(reserva: T): Boolean

    fun listar(): List<Reserva>
}