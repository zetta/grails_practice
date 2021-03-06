package com.toptel.curso.practica.calculable


class DepositoCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos, BigDecimal saldoTotal)
	{
		def total = 0.0g
		movimientos.each
		{
			movimiento ->
			if('deposito' == movimiento.tipoMovimiento)
			{
				total += movimiento.monto
			}
		}
		total
	}
}
