package ar.edu.unq.eperdemic.modelo.evento.tipoEvento

import ar.edu.unq.eperdemic.modelo.evento.Accion
import ar.edu.unq.eperdemic.modelo.evento.Evento

class Arribo : TipoEvento() {
    override fun log(evento: Evento): String {
        return when (evento.accionQueLoDesencadena) {
            Accion.ARRIBO.name -> "El vector de id ${evento.idVectorQueSeMueve} se movio desde la ubicacion ${evento.ubicacionOrigen} hacia la ubicacion ${evento.ubicacionDestino}"
            else -> ""
        }
    }
}