package com.toptel.curso.practica.controllers

import grails.converters.JSON 

class ServicioController {

	def cuentaBancariaService
	
    def index = { 
	
		def year = params.int('year') ? params.int('year') : 2011
		def month = params.int('month') ? params.int('month') : 1
		def account = params.account ? params.account : '0102030405'
		
		def resultado = cuentaBancariaService.calcularEstadoCuenta(year,month,account)
		
		response.setHeader("content-type", "text/xml")
		render resultado.estadoCuenta
			
	}
}
