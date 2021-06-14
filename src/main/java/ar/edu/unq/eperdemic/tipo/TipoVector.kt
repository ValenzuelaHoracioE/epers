package ar.edu.unq.eperdemic.tipo

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.TipoCamino
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.utility.random.RandomMaster
import ar.edu.unq.eperdemic.utility.random.RandomMasterImpl

abstract class TipoVector(){
    var randomGenerator : RandomMaster = RandomMasterImpl
    abstract var posiblesCaminos :List<TipoCamino>

    fun posiblesCaminos():List<TipoCamino>{
        return posiblesCaminos
    }

    fun contagiamePor(vectorAContagiar: Vector, tipoDelContagiador: TipoVector, especiesContagiador: List<Especie>) : List<Pair<Vector, Especie>> {
        var vectoresYEspecies = listOf<Pair<Vector, Especie>>()
        if(this.puedeSerContagiadoPor(tipoDelContagiador)) {
            especiesContagiador.forEach{
                especie -> vectoresYEspecies += this.infectameSiCorresponde(vectorAContagiar, especie)
            }
        }
        return vectoresYEspecies
    }

    fun infectameSiCorresponde(vectorAContagiar: Vector, especie: Especie) : List<Pair<Vector, Especie>> {
        var vectorYEspecie = listOf<Pair<Vector, Especie>>()
        if (this.porcentajeDeContagioExitoso(especie) >= randomGenerator.giveMeARandonNumberBeetween(1.0,100.0)) {
            vectorYEspecie += vectorAContagiar.infectarse(especie)
        }
        return vectorYEspecie
    }

    fun porcentajeDeContagioExitoso(especie: Especie): Double = randomGenerator.giveMeARandonNumberBeetween(1.0,10.0) + this.factorContagio(especie)

    open fun esHumano(): Boolean = false

    open fun esInsecto() : Boolean = false

    open fun esAnimal() : Boolean = false

    abstract fun factorContagio(especie : Especie): Int

    abstract fun puedeSerContagiadoPor(tipo : TipoVector): Boolean

    open fun agregarInfectado(especie : Especie){}

    fun puedeAtravesar(tipoCamino: TipoCamino) = tipoCamino.puedeSerAtravesadoPor(this)
}