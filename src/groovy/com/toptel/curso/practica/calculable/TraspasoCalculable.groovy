package com.toptel.curso.practica.calculable


class TraspasoCalculable extends AbstractCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos)
	{
		def total = 0.0g
		movimientos.each
		{
			movimiento ->
			if('traspaso' == movimiento.tipoMovimiento)
			{
				total -= movimiento.monto
			}
		}
		total
	}
	
}
