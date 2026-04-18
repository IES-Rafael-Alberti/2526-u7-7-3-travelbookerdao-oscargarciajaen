package es.iesra.presentacion

import es.iesra.repositorio.ReservaRepository
import es.iesra.servicio.IReservaService

/**
 * Implementación de la interfaz IConsolaUI.
 * Se inyecta una instancia de IReservaService para cumplir con la inversión de dependencias.
 */
class ConsolaUI(private val reservaService: IReservaService) : IUserInterface {

    val repository = ReservaRepository()

    override fun iniciar() {
        var salir = false
        while (!salir) {
            mostrarMenu()
            when (leerOpcion()) {
                1 -> crearReserva()
                2 -> listarReservas()
                3 -> eliminarReserva()
                4 -> actualizarReserva()
                5 -> {
                    println("Saliendo de la aplicación. ¡Hasta luego!")
                    salir = true
                }

                else -> println("Opción no válida. Intente nuevamente.")
            }
        }
    }

    private fun mostrarMenu() {
        println("\n----- Gestor de Reservas -----")
        println("1. Crear nueva reserva")
        println("2. Listar reservas")
        println("3. Eliminar reserva")
        println("4. Actualizar reserva")
        println("5. Salir")
        print("Seleccione una opción: ")
    }

    private fun leerOpcion(): Int = try {
        readln().toInt()
    } catch (e: Exception) {
        -1
    }

    /**
     * Método para crear una reserva, preguntando al usuario el tipo de reserva a crear.
     */
    private fun crearReserva() {
        println("\nSeleccione el tipo de reserva a crear:")
        println("1. Reserva de Vuelo")
        println("2. Reserva de Hotel")
        print("Opción: ")
        when (leerOpcion()) {
            1 -> {
                print("Ingrese la descripción (itinerario) de la reserva de vuelo: ")
                val descripcion = readln()
                print("Ingrese el origen: ")
                val origen = readln()
                print("Ingrese el destino: ")
                val destino = readln()
                print("Ingrese la hora de vuelo (HH:mm): ")
                val horaVuelo = readln()
                try {
                    reservaService.crearReservaVuelo(descripcion, origen, destino, horaVuelo)
                    println("Reserva de vuelo creada exitosamente.")
                } catch (e: IllegalArgumentException) {
                    println("Error al crear la reserva: ${e.message}")
                }
            }

            2 -> {
                print("Ingrese la descripción de la reserva de hotel: ")
                val descripcion = readln()
                print("Ingrese la ubicación: ")
                val ubicacion = readln()
                print("Ingrese el número de noches: ")
                val numeroNoches = try {
                    readln().toInt()
                } catch (e: Exception) {
                    println("Número inválido de noches, se asignará 1 por defecto.")
                    1
                }
                try {
                    reservaService.crearReservaHotel(descripcion, ubicacion, numeroNoches)
                    println("Reserva de hotel creada exitosamente.")
                } catch (e: IllegalArgumentException) {
                    println("Error al crear la reserva: ${e.message}")
                }
            }

            else -> println("Opción no válida.")
        }
    }

    fun eliminarReserva(){
        println("\nSeleccione el tipo de reserva a eliminar:")
        println("1. Reserva de Vuelo")
        println("2. Reserva de Hotel")
        print("Opción: ")
        when (leerOpcion()) {
            1 -> {
                    val vuelos = reservaService.obtenerVuelos()
                    vuelos.forEach { println(it) }
                    print("Introduce el id del vuelo que quieras eliminar")
                    val id = readLine()
                    if (id != null && id.toInt() > 0 && id.toInt() < vuelos.size) {
                        repository.eliminarReservaVuelo(id)
                    } else {
                        println("Id no válido")
                    }
                }
            2 -> {
                    val hoteles = repository.obtenerHoteles()
                    hoteles.forEach {println(it)}
                    print("Introduce el id del vuelo que quieras eliminar")
                    val id = readLine()
                    if (id != null && id.toInt() > 0 && id.toInt() < hoteles.size) {
                        repository.eliminarReservaHotel(id)
                    } else {
                        println("Id no válido")
                    }
            }

            else -> println("Opción no válida.")
        }
    }

    fun actualizarReserva(){
        println("\nSeleccione el tipo de reserva a eliminar:")
        println("1. Reserva de Vuelo")
        println("2. Reserva de Hotel")
        print("Opción: ")
        when (leerOpcion()) {
            1 -> {
                val vuelos = repository.obtenerVuelos()
                vuelos.forEach { println(it) }
                print("Introduce el id del vuelo que quieras eliminar")
                val id = readLine()
                if (id != null && id.toInt() > 0 && id.toInt() < vuelos.size) {
                    repository.actualizarVuelo(id)
                } else {
                    println("Id no válido")
                }
            }
            2 -> {
                val hoteles = repository.obtenerHoteles()
                hoteles.forEach {println(it)}
                print("Introduce el id del vuelo que quieras eliminar")
                val id = readLine()
                if (id != null && id.toInt() > 0 && id.toInt() < hoteles.size) {
                    repository.actualizarHotel(id)
                } else {
                    println("Id no válido")
                }
            }

            else -> println("Opción no válida.")
        }
    }

    /**
     * Método para listar todas las reservas registradas.
     */
    fun listarReservas() {
        val reservas = repository.obtenerTodas()
        println("\n--- Lista de Reservas ---")
        if (reservas.isEmpty()) {
            println("No hay reservas registradas.")
        } else {
            reservas.forEach { println(it) }
        }
    }
}
