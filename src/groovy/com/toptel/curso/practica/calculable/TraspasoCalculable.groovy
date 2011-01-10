package com.toptel.curso.practica.calculable


class TraspasoCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos, BigDecimal saldoTotal)
	{
		def total = 0.0g
		movimientos.each
		{
			movimiento ->
			if('traspaso-propio' == movimiento.tipoMovimiento || 'traspaso-terceros' == movimiento.tipoMovimiento)
			{
				total -= movimiento.monto
			}
		}
		total
	}
	
}
