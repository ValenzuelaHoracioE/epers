package ar.edu.unq.eperdemic.persistencia.dao

import ar.edu.unq.eperdemic.modelo.Mutacion

interface MutacionDAO {
    fun crear(mutacion: Mutacion): Mutacion
    fun recuperar(mutacionId: Int): Mutacion
    fun actualizar(mutacion: Mutacion)
}