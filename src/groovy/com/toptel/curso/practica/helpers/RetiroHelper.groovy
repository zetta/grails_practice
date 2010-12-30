package com.toptel.curso.practica.helpers

import com.toptel.curso.practica.domain.Movimiento;
import org.joda.time.*

class RetiroHelper
{
	
	double calculaRetiros(int year, int month)
	{
		def fecha  = new LocalDate(year,month,1)
		
		def criteria  = Movimiento.createCriteria()
		def retiros = criteria.list
		{
			eq("tipoMovimiento","RETIRO")
			// TODO el betweeen debe considerar la hora 00:00 para fecha inicial y al final a las 23:59
			between("fecha",fecha.dayOfMonth().withMinimumValue(),fecha.dayOfMonth().withMaximumValue())
			order('fecha','asc')
		}
		
		//def retiros = Movimiento.findAllByTipoMovimientoAndFecha("RETIRO",)
		
	}
	
	
}
