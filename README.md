PRACTICA
========

- Crear una aplicación Grails con las siguientes características

- Crear el modelo de dominio (GORM)  para almacenar la siguiente información:

  - Información que describa a una persona (Nombre, domicilio, rfc, etc)
  - Información sobre cuentas de banco (en que banco están, su limite de crédito, fecha de apertura, etc)
  - Información sobre movimientos bancarios (depósitos, retiros, traspasos)

- Definir un servicio Grails cuya función será la siguiente:

  - Calcular el estado de cuenta mensual
  - Este cálculo lo realizará mediante clases "ayudantes" que serán beans de Spring que realizarán un cálculo cada una de ellas, bajo las siguientes reglas:
    - Cada bean de spring solo realizará un tipo de cálculo
    - Al inicializarse cada bean de Spring, deberá validar que todos los recursos que necesite para trabajar, ya sean objetos de dominio que apoyen sus cálculos, inyecciones de otros beans, etc.
    - Cuando se presente un error en el cálculo, arrojar una excepción 
    - Las reglas deben ejecutarse en un orden predefinido (Existe una interface llamada Ordered que les puede ser de utilidad)
    - Los beans de Spring y sus dependencias deben declararse en el archivo resources.groovy mediante el DSL de Spring [http://grails.org/doc/1.3.5/guide/14.%20Grails%20and%20Spring.html]

- El servicio Grails deberá ejecutar una por una de manera transaccional, y al detectar una excepción debe hacer rollback y abortar la ejecución (http://grails.org/doc/1.3.5/guide/8.%20The%20Service%20Layer.html)
- El servicio Grails solo gestionará un List o un Set ordenado, permitiendo que se inyecten tantos beans de Spring como necesite para ejecutar sus reglas, pero sin limitarlo a que se inyecten más
- El servicio Grails debe arrojar un pequeño reporte a consola al iniciar indicando los beans de reglas que le fueron cargados, y en que orden (Tip: Pueden utilizar InitializingBean u otras herramientas de Spring)
- El estado de cuenta debe ser un archivo XML, y además, este archivo debe ser validado por un esquema XSD.
- El código para validar un XML en base a un XSD es el siguiente:

    import javax.xml.XMLConstants 
    import javax.xml.transform.stream.StreamSource 
    import javax.xml.validation.SchemaFactory 
    import org.springframework.core.io.ClassPathResource 

    def classpathRes = new ClassPathResource("/ruta/en/classpath/a/su/archivo.xsd")
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        def schema = factory.newSchema(new StreamSource(classpathRes.getInputStream()))
    def validator = schema.newValidator()
    validator.validate(new StreamSource(new StringReader(cadenaXml)))
- El estado de cuenta debe tener perfectamente identificados los siguientes elementos:
  - Movimientos y su tipo (deposito, retiro, traspaso)
  - Cargos y abonos (impuestos, intereses, etc)
  - Saldo final



- Las reglas a ejecutar para el cálculo del estado de cuenta son las siguientes:
  - Por cada traspaso a una cuenta de la misma persona se debe hacer un cargo de $5.00
  - Por cada traspaso a una cuenta de terceros se debe hacer un cargo de $10.00
  - Si la cuenta a final del periodo tiene un saldo negativo, debe calculárse un interés del 15%
  - Si la cuenta a final del periodo tiene un saldo positivo, debe calculárse un interés del 10%
  - Al interés por cobrar se le debe calcular el IVA al 16%
  - Al interés a favor se le debe restar 10% por concepto de ISR
  - Si la persona hizo depósitos mayores a $15,000.00 se le debe hacer un cargo del 3% por concepto de IDE al monto que lo sobrepasa (por ejemplo, si deposita 17,000, se le saca el 3% a los 2,000.00 que se exceden)
  - El IDE debe compensarse contra el ISR generado (es decir, tiene $2,000.00 de ISR y $ 1,500.00 de IDE, al compensar, solo debe pagar $ 0.00 de ISR y $ 500.00 de IDE)


- Todo debe estar cubierto por pruebas unitarias

Referencias:

- API de Spring Framework
http://static.springsource.org/spring/docs/2.5.x/api/index.html

- Guía de Grails
http://grails.org/doc/1.3.5/guide/
http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/

