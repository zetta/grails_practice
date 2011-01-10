package com.toptel.curso.practica.calculable

import com.toptel.curso.practica.domain.Movimiento 
import org.joda.time.DateTime 
		
	
class InteresCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos, BigDecimal saldoTotal)
	{
		def total = 0.0g
		def interest = saldoTotal <0?15:10;
		def monto = Math.abs( saldoTotal*(interest/100)  )
		def movimiento = new Movimiento(
			tipoMovimiento: (15==interest)?'interes':'rendimiento',
			monto: monto,
			fecha: new DateTime() ,	
			concepto: "Comision Por Manejo de Cuenta (${interest}%)",
			cuenta: movimientos[ movimientos.size()-1 ].cuenta
		)
		movimientos << movimiento
		total = (15==interest)? total-monto : total+monto
		total
	}
	
}
