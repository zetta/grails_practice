package com.toptel.curso.practica.domain

import org.joda.time.DateTime 

class Cuenta {

	int limiteCredito
	String banco
	double saldo
	DateTime fechaApertura

	static belongsTo = [persona:Persona]
	static hasMany = [movimientos: Movimiento]

    static constraints = {
    }
}
