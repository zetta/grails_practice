package com.toptel.curso.practica.calculable


class ImpuestoCalculable extends AbstractCalculable implements Calculable 
{

	BigDecimal calcula(List movimientos)
	{
		def total = 0.0g
		movimientos.each
		{
			movimiento ->
			/*
			if('deposito' == movimiento.tipoMovimiento)
			{
				total += movimiento.monto
			}*/
		}
		total
	}
	
}
