package ar.edu.unq.eperdemic.services.impl

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.PatogenoService

class PatogenoServiceJDBCImpl(var patogenoDAO: PatogenoDAO) : PatogenoService {
    override fun crearPatogeno(patogeno: Patogeno): Int {
        return patogenoDAO.crear(patogeno)
    }

    override fun recuperarPatogeno(id: Int): Patogeno {
        return patogenoDAO.recuperar(id)
    }

    override fun recuperarATodosLosPatogenos(): List<Patogeno> {
        return patogenoDAO.recuperarATodos()
    }

    override fun agregarEspecie(id: Int, nombreEspecie: String, paisDeOrigen: String): Especie {
        val patogenoAAgregarEspecie = patogenoDAO.recuperar(id)
        val especieResultado = patogenoAAgregarEspecie.crearEspecie(nombreEspecie, paisDeOrigen)
        patogenoDAO.actualizar(patogenoAAgregarEspecie)
        return especieResultado
    }

    override fun cantidadDeInfectados(especieId: Int): Int {
//        TODO("Not yet implemented")
        return 0
    }

    override fun esPandemia(especieId: Int): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun recuperarEspecie(id: Int): Especie {
//        TODO("Not yet implemented")
        return Especie()
    }

    override fun crearEspecie(especie: Especie): Int {
//        TODO("Not yet implemented")
        return 0
    }

    override fun actualizarEspecie(especie: Especie) {
//        TODO("Not yet implemented")
    }
}