package ar.edu.unq.eperdemic.modelo.exception

class NoExisteUbicacion(ubicacion_nombre:String):RuntimeException ("ubicacion: $ubicacion_nombre no existe")

