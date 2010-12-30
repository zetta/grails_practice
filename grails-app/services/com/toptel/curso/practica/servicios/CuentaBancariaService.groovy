package com.toptel.curso.practica.servicios

import  com.toptel.curso.practica.helpers

class CuentaBancariaService implements ServicioBanco {

    static transactional = true


	RespuestaServicio calcularEstadoCuenta (int year, int month)
	{
		def respuesta = new RespuestaServicio();
		def helper = new CalculadorHelper()
		respuesta.estadoCuenta =  helper.calcula(year,month)
		
	}


}
