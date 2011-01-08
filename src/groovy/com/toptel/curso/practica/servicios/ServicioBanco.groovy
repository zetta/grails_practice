package com.toptel.curso.practica.servicios


interface ServicioBanco
{
	/**
	 * Servicio para obtener el estado de cuenta por mes y año solicitado
	 * @param int year año 
	 * @param int month numero de mes 1 = Enero 12 = Diciembre
	 * @param String accountNumber
	 * @returns RespuestaServicio
	 */
	RespuestaServicio calcularEstadoCuenta(int year, int month, String accountNumber)
	
	
}
