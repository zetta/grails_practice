package com.toptel.curso.practica.domain

import org.joda.time.DateTime 

class Cuenta {

	int limiteCredito
	String accountNumber
	String banco
	double saldo
	DateTime fechaApertura

	static belongsTo = [titular: Persona]
	static hasMany = [movimientos: Movimiento]

    static constraints = {
    	accountNumber(matches: "^\\d+\$")
    }
}
