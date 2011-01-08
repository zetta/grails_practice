package com.toptel.curso.practica.domain

import org.joda.time.DateTime 

class Movimiento {

	String tipoMovimiento
	double monto
	DateTime fecha
	String concepto

	static belongsTo = [cuenta:Cuenta]

    static constraints = {
    }
    
    String toString()
    {
    	"${tipoMovimiento} (${monto})"
    }
    
}
