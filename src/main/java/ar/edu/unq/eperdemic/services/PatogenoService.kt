package ar.edu.unq.eperdemic.services

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno

interface PatogenoService {
    fun crearPatogeno(patogeno: Patogeno): Int
    fun recuperarPatogeno(id: Int): Patogeno
    fun recuperarATodosLosPatogenos(): List<Patogeno>
    fun agregarEspecie(idPatogeno: Int, nombreEspecie: String, paisDeOrigen : String) : Especie

    fun cantidadDeInfectados (especieId: Int) : Int
    fun esPandemia (especieId: Int) : Boolean
    fun recuperarEspecie(id: Int): Especie

    fun crearEspecie(especie : Especie) : Int
    fun actualizarEspecie(especie: Especie)
}