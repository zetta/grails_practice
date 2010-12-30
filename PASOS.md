Pasos a Seguir para generar la practica
=======================================

Creacion de aplicacion y objetos de dominio

1. ´grails create-app grails_practice´
1. ´cd create-app grails_practice´
1. ´grails create-domain-class com.toptel.curso.practica.domain.Persona´
1. ´grails create-domain-class com.toptel.curso.practica.domain.Cuenta´
1. ´grails create-domain-class com.toptel.curso.practica.domain.Movimiento´

Instalar plugin *joda-time*

1. ´grails install-plugin joda-time´

Declarar atributos de los objetos de dominio ...

Generar el Servicio 

1. ´grails create-service com.toptel.curso.practica.servicios.CuentaBancaria´ 

Que implementa una interfaz de negocio *ServicioBanco*.

dentro del paquete  com.toptel.curso.practica.servicios se crea la clase RespuestaServicio
que es el objeto que regresará el servicio al terminar.


Se crea el paquete com.toptel.curso.practica.helpers con las clases

 - CalculadorGlobalHelper **obtendra los calculos y las transacciones para generar el xml del mes y año especificados**
 - DepositoHelper **calcula los depositos por mes y año y devuele las transacciones**
 - TraspasoHelper **calcula los traspasos por mes y año y devuele las transacciones**
 - RetiroHelper **calcula los retiros por mes y año y devuele las transacciones**


