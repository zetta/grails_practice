package com.toptel.curso.practica.helpers


class CalculadorGlobalHelper
{
	
	
	DepositoHelper depositoHelper
	RetiroHelper retiroHelper
	TraspasoHelper traspasoHelper
	
	
	Byte[] calcula(int year, int month)
	{
		// TODO guardar los saldos iniciales en algun lugar
		def saldoInicial = 5000
		def depositos = depositoHelper.calculaDepositos(year,month)
		def retiros = retiroHelper.calculaRetiros(year,month)
		def traspasos = traspasoHelper.calculaTraspasos(year,month)
		
		
		def total = saldoInicial + depositos - retiros - traspasos
	}
	
}
