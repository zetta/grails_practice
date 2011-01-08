package com.toptel.curso.practica.domain

import org.joda.time.DateTime 

class Persona {

	String nombre
	String domicilio
	String rfc
	DateTime fechaNacimiento
	String email
	String telefono
	

	static hasMany = [cuentas:Cuenta]

    static constraints = {
    	
    	rfc(maxSixe:13, matches:"[A-Z]{3,4}\\d{6}.{3}")
    	telefono(matches: "^\\d+\$")
    }
}
