package com.toptel.curso.practica.calculable


class RetiroCalculable extends AbstractCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos)
	{
		def total = 0.0g
		movimientos.each
		{
			movimiento ->
			if('retiro' == movimiento.tipoMovimiento)
			{
				total -= movimiento.monto
			}
		}
		total
	}
	
}
