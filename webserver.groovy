import io.vertx.groovy.ext.web.Router
import io.vertx.groovy.ext.web.handler.BodyHandler
import io.vertx.core.json.Json
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.handler.StaticHandler


Integer counter = 0

//routers
def server = vertx.createHttpServer()
def router = Router.router(vertx)
router.route().handler(BodyHandler.create())

//Event Bus
def eb = vertx.eventBus()

//Variables A utilizar
Integer a1
Integer a2
Integer a3
Integer extra
Integer areatotal=0

//Verticle-Consumer/Handler
eb.consumer("com.makingdevs") { message ->
        println "Linea procesando:"+message.body()
        //obtenemos los valores de cada una de las tres áreas
	a1= message.body().split("x")[0].toInteger() * message.body().split("x")[1].toInteger()
	a2= message.body().split("x")[1].toInteger() * message.body().split("x")[2].toInteger()
	a3= message.body().split("x")[0].toInteger() * message.body().split("x")[2].toInteger()
	//obteniendo el valor minimo de las áreas que será el extra
	extra=Math.min( a1, Math.min(a2,a3))
	//obtenemos los valores de las áreas multiplicado por dos
	a1=a1*2
	a2=a2*2
	a3=a3*2
        //Area total
	areatotal=areatotal+a1+a2+a3+extra
        Thread.sleep( 1000);  
        //Comunicando a otro verticle
        eb.send("com.respuesta", areatotal)
}

router.route("/prueba1").handler { routingContext ->
      routingContext.response()
      .setStatusCode(201)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily([msg:"Numero: "+counter]))
      counter++

}

server.requestHandler(router.&accept).listen(8080)
