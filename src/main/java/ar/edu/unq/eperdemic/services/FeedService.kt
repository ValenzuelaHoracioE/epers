package ar.edu.unq.eperdemic.services

import ar.edu.unq.eperdemic.modelo.evento.Evento

interface FeedService {

    fun feedPatogeno(tipoDePatogeno: String) : List<Evento>
    fun feedVector(vectorId: Long): List<Evento>
    fun feedUbicacion(nombreDeUbicacion: String): List<Evento>
    fun agregarEvento(eventoContagioPorPandemia: Evento) : Evento
    fun especieYaEstabaEnLaUbicacion(nombreUbicacion: String, tipoPatogenoDeLaEspecie: String, nombreDeLaEspecie: String): Boolean
    fun vectorFueContagiadoAlMover(nombreUbicacion: String, idVectorInfectado:Int, idVectorAInfectar: Int): Boolean
    fun especieYaTieneEventoPorPandemia(tipoPatogenoDeLaEspecie: String, nombreEspecie : String) : Boolean
}