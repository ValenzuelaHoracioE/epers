package ar.edu.unq.eperdemic.tipo.transformer

import ar.edu.unq.eperdemic.modelo.exception.ClaveRepetidaDeTipoException
import ar.edu.unq.eperdemic.modelo.exception.TipoNoEncontradoException
import ar.edu.unq.eperdemic.tipo.TipoVector
import ar.edu.unq.eperdemic.utility.Delivery

class TipoDelivery(tiposList : List<TipoVector>) : Delivery<TipoVector>(tiposList) {
    override fun myAddException(nombreTipo: String) = ClaveRepetidaDeTipoException(nombreTipo)
    override fun myGetException(nombreTipo: String) = TipoNoEncontradoException(nombreTipo)
}