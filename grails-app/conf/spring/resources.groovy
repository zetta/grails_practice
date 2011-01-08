// Place your Spring DSL code here
beans = {

	calculadorHelper(com.toptel.curso.practica.helpers.CalculadorHelper)
	{
		calculables = [
			ref("depositoCalculable"),
			ref("retiroCalculable"),
			ref("traspasoCalculable"),
			ref("impuestoCalculable"),
			ref("interesCalculable")
		]	
	}
	
	// calculables
	depositoCalculable(com.toptel.curso.practica.calculable.DepositoCalculable)
	retiroCalculable(com.toptel.curso.practica.calculable.RetiroCalculable)
	traspasoCalculable(com.toptel.curso.practica.calculable.TraspasoCalculable)
	impuestoCalculable(com.toptel.curso.practica.calculable.ImpuestoCalculable)
	interesCalculable(com.toptel.curso.practica.calculable.InteresCalculable)

}
