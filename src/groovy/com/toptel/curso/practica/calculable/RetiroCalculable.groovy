package com.toptel.curso.practica.calculable


class RetiroCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos, BigDecimal saldoTotal)
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
