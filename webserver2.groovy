import io.vertx.groovy.ext.web.Router
import io.vertx.groovy.ext.web.handler.BodyHandler
import io.vertx.core.json.Json
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.handler.StaticHandler

//Event Bus
def eb = vertx.eventBus()
//Verticle Sender
def fichero = new File ("dia2.txt")
//Iterando las lineas del fichero
fichero.eachLine(){
 eb.send("com.makingdevs", it)
}

eb.consumer("com.respuesta", { message ->
  println "Respuesta acumulativa: "+message.body()
 })
