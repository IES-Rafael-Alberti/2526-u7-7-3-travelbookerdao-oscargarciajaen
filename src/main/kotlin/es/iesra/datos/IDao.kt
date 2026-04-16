package es.iesra.datos

interface IDao<T> {

    fun salvar(reserva: T): Boolean

    fun eliminar(reserva: T): Boolean

    fun actualizar(reserva: T): Boolean

    fun listar(): List<String>
}