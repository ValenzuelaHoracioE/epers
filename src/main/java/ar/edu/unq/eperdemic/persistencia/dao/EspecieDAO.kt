package ar.edu.unq.eperdemic.persistencia.dao

import ar.edu.unq.eperdemic.modelo.Especie

interface EspecieDAO {

    fun crearEspecie(especie : Especie) : Int

    fun recuperarEspecie(id : Int) : Especie

    fun actualizar(especie: Especie)

    fun cantidadDeInfectados(especie: Especie) : Int

    fun esPandemia(especie: Especie) : Boolean
}