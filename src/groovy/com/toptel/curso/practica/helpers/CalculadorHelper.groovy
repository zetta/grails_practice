package com.toptel.curso.practica.helpers

import com.toptel.curso.practica.calculable.Calculable
import com.toptel.curso.practica.domain.Movimiento
import com.toptel.curso.practica.domain.Cuenta
import org.joda.time.*
import org.joda.time.format.*
import groovy.xml.MarkupBuilder

class CalculadorHelper
{
	
	List<Calculable> calculables
	
	def calcula(int year, int month, String accountNumber)
	{
	
		def saldoTotal = 0.0g
		
	
		def account = Cuenta.findByAccountNumber(accountNumber)
		def titular = account.titular
		def fecha  = new LocalDate(year,month,1)

		def criteria  = Movimiento.createCriteria()
		def movements = criteria.list
		{
			cuenta
			{
				eq('accountNumber',accountNumber)
			}
			between("fecha",
					fecha.dayOfMonth().withMinimumValue().toDateTimeAtCurrentTime(),
					fecha.dayOfMonth().withMaximumValue().toDateTimeAtCurrentTime())
			order('fecha','asc')
		}
		
		
		
		calculables.each{
			println "Ejecutando el calculo de ${it.getClass()}"
			saldoTotal += it.calcula(movements)
		}
		
		
		
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		DateTimeFormatter dtf  = DateTimeFormat.forPattern("MMMM, dd  yyyy")
		builder.cuenta
		{
			nombreTitular(titular.nombre)
			numeroCuenta(account.accountNumber)
			saldo(saldoTotal)
			movimientos
			{
				movements.each
				{
					def dateWriter = new StringWriter()
					dtf.printTo(dateWriter,it.fecha)
					movimiento(
						monto: it.monto,
						tipo: it.tipoMovimiento,
						fecha: dateWriter.toString(),
						concepto: it.concepto
					)
				}	
			}
		}
		
		return writer.toString()
		
		
		
	}
	
}
