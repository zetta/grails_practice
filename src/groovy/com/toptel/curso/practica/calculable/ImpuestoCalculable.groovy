package com.toptel.curso.practica.calculable

import com.toptel.curso.practica.domain.Movimiento 


class ImpuestoCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos, BigDecimal saldoTotal)
	{
		def total = 0.0g
		def movs = []
		movimientos.each
		{
			movimiento ->
			if('deposito' == movimiento.tipoMovimiento)
			{
				if(movimiento.monto > 15000)
				{
					def resta = movimiento.monto-15000
					def monto = Math.abs(resta*0.03)
					def mov = new Movimiento(
						tipoMovimiento: 'impuesto-ide',
						monto: monto,
						fecha: movimiento.fecha,
						concepto: 'IDE 3% '+resta,
						cuenta: movimiento.cuenta
					)
					movs << mov
					total -= monto
				}
				total += movimiento.monto
			}
		}
		movs.each { movimientos << it }
		total
	}
	
}
