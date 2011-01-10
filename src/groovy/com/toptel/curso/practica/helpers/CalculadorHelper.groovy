package com.toptel.curso.practica.helpers

//import java.math.BigInteger;
import com.toptel.curso.practica.calculable.Calculable
import com.toptel.curso.practica.domain.Movimiento
import com.toptel.curso.practica.domain.Cuenta
import org.joda.time.*
import org.joda.time.format.*
import org.springframework.beans.factory.InitializingBean;
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import org.springframework.core.io.ClassPathResource

import groovy.xml.MarkupBuilder

class CalculadorHelper implements InitializingBean
{
	
	def titular;
	def account;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		println "properties setted!"
	}
	List<Calculable> calculables
	
	def calcula(int year, int month, String accountNumber)
	{
		def saldoTotal = 0.0g
		
		account = Cuenta.findByAccountNumber(accountNumber)
		titular = account.titular
		def fecha  = new LocalDate(year,month,1)

		def criteria  = Movimiento.createCriteria()
		def movements = criteria.list
		{
			cuenta
			{
				eq('accountNumber',accountNumber)
			}
			between("fecha",
					fecha.dayOfMonth().withMinimumValue().toDateTimeAtCurrentTime(),
					fecha.dayOfMonth().withMaximumValue().toDateTimeAtCurrentTime())
			order('fecha','asc')
		}
		
		println "Found ${movements.size()} movements"
		
		calculables.each{
			println "Ejecutando el calculo de ${it.getClass()}"
			saldoTotal += it.calcula(movements, saldoTotal)
		}
		
		
		def xml = createXml(saldoTotal,movements) 
		validateXSD(xml)
		return xml
	}
	
	
	protected createXml(saldoTotal,movements)
	{
		movements.sort{it.fecha}
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		DateTimeFormatter dtf  = DateTimeFormat.forPattern("MMMM, dd  yyyy")
		builder.cuenta
		{
			nombreTitular(titular.nombre)
			numeroCuenta(account.accountNumber)
			saldo(new Float(saldoTotal))
			movimientos(total:movements.size())
			{
				movements.each
				{
					def dateWriter = new StringWriter()
					dtf.printTo(dateWriter,it.fecha)
					movimiento(
						monto: new Float(it.monto),
						tipo: it.tipoMovimiento,
						fecha: dateWriter.toString(),
						concepto: it.concepto
					)
				}
			}
		}
		
		return writer.toString()
	}
	
	
	protected validateXSD(xml)
	{
		def classpathRes = new ClassPathResource("com/toptel/curso/practica/xsd/account.xsd")
		def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
		def schema = factory.newSchema(new StreamSource(classpathRes.getInputStream()))
		def validator = schema.newValidator()
		validator.validate(new StreamSource(new StringReader(xml)))
	}
	
	
}
