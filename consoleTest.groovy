import grails.converters.*
def bootstrap = new BootStrap()

println "inicializando datos..."
bootstrap.init()
println "datos iniciados"

try
{
   def servicio = ctx.getBean('cuentaBancariaService')
   println servicio.calcularEstadoCuenta(2011,1,'0102030406') as JSON
} catch(Exception e)
{
    e.printStackTrace()
}

println "ejecucion finalizada, borrando datos"
bootstrap.destroy()
println "done"