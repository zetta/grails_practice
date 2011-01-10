package com.toptel.curso.practica.calculable

import com.toptel.curso.practica.domain.Movimiento 
import org.joda.time.DateTime 

class ComisionCalculable implements Calculable 
{

	BigDecimal calcula(List<Movimiento> movimientos, BigDecimal saldoTotal)
	{
		def total = 0.0g
		def movs = []
		movimientos.each
		{
			movimiento ->
			switch(movimiento.tipoMovimiento)
			{
				case 'traspaso-terceros':
				case 'traspaso-propio':
					def monto = ('traspaso-terceros' == movimiento.tipoMovimiento) ? 10 : 5
					def mov = new Movimiento(
						tipoMovimiento: 'comision',
						monto: monto,
						fecha: movimiento.fecha,
						concepto: 'Comision Traspaso',
						cuenta: movimiento.cuenta
					)
					movs << mov
					total -= monto
				break;
				case 'interes':
					def monto = Math.abs(movimiento.monto*0.16)
					def mov = new Movimiento(
						tipoMovimiento: 'comision',
						monto: monto,
						fecha: movimiento.fecha,
						concepto: 'IVA 16%',
						cuenta: movimiento.cuenta
					)
					movs << mov
					total -= monto
				break;
				case 'rendimiento':
					def monto = Math.abs(movimiento.monto*0.1)
					def mov = new Movimiento(
						tipoMovimiento: 'comision',
						monto: monto,
						fecha: movimiento.fecha,
						concepto: 'ISR 10%',
						cuenta: movimiento.cuenta
					)
					movs << mov
					total -= monto
				break;
			}
		}
		movs.each { movimientos << it }
		total
	}
	
}
