
import com.toptel.curso.practica.domain.Persona
import com.toptel.curso.practica.domain.Cuenta
import com.toptel.curso.practica.domain.Movimiento
import org.joda.time.DateTime 

class BootStrap {

    def init = { servletContext ->
    
    	// creamos un poco de datos de prueba
    	
    	def ran = {
     		int first, int second ->
     		new Random().nextInt(second-first+1)+first
		}

    	
    	[
    	 [nombre: 'Pedro Perez', domicilio:'conocido',rfc:'CEJJ860613AN7',email:'pedro@correo.de',telefono:'1122334455',cuenta:'0102030405'],
    	 [nombre: 'Juan Perez', domicilio:'conocido',rfc:'CEJJ860613AN6',email:'juan@correo.de',telefono:'1122334455',cuenta:'0102030406'],
    	 [nombre: 'Jose Perez', domicilio:'conocido',rfc:'CEJJ860613AN5',email:'jose@correo.de',telefono:'1122334455',cuenta:'0102030407'],
    	 [nombre: 'Ruben Perez', domicilio:'conocido',rfc:'CEJJ860613AN4',email:'ruben@correo.de',telefono:'1122334455',cuenta:'0102030408'],
    	 [nombre: 'Raul Perez', domicilio:'conocido',rfc:'CEJJ860613AN3',email:'raul@correo.de',telefono:'1122334455',cuenta:'0102030409'],
    	 [nombre: 'Miguel Perez', domicilio:'conocido',rfc:'CEJJ860613AN3',email:'miguel@correo.de',telefono:'1122334455',cuenta:'0102030410']
    	].each
    	{
    		def persona = new Persona(
    			nombre: it.nombre,
    			domicilio: it.domicilio,
    			rfc: it.rfc,
    			email: it.email,
    			fechaNacimiento: new DateTime(),
    			telefono: it.telefono,
    			cuentas: []
    		)
    		
    		def limite = (ran(5,100)*1000)
    		
    		def cuenta = new Cuenta(
    			titular: persona,
				limiteCredito: limite,
				banco: 'BBVA Bancomer',
				saldo: ran(0,limite), // este valor SI deberia coincidir ... pero bueno.. 
				fechaApertura: new DateTime(),
				accountNumber: it.cuenta,
				movimientos: []
			)
			persona.cuentas << cuenta 
    		
    		(1..300).each{
    			def movimiento = new Movimiento(
    				tipoMovimiento: ['retiro','deposito','traspaso'][ ran(0,2) ],
    				monto: ran(50,40000),
    				fecha: new DateTime().minusDays( ran(0,80) ).minusHours(ran(0,12)).minusSeconds(ran(0,500)) ,
    				concepto: 'Lorem Ipsum Dolor',
    				cuenta: cuenta
    			)
    			cuenta.movimientos << movimiento
    			//print '.'
    		}
    		
    		if(persona.validate())
    		{
    			persona.save()
    			println "saved person '${persona.nombre}' (${persona.id}) acc [${cuenta.accountNumber}][${cuenta.saldo}]"
    			//println cuenta.movimientos	
    		}
    		else
    		{
    			persona.errors.each{ println it }
    		}
    	}
    
    }
    def destroy = {
    	Movimiento.list().each{ it.delete() }
    	Cuenta.list().each{  it.delete() }
    	Persona.list().each{ it.delete() }
    	
    }
}
