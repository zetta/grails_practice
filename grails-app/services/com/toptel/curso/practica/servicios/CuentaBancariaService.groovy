package com.toptel.curso.practica.servicios



class CuentaBancariaService implements ServicioBanco {

    static transactional = true
	def calculadorHelper

	RespuestaServicio calcularEstadoCuenta (int year, int month, String accountNumber)
	{
		def respuesta = new RespuestaServicio()
		respuesta.estadoCuenta =  calculadorHelper.calcula(year,month,accountNumber)
		return respuesta
	}


}
