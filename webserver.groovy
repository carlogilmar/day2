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

//Verticle-Consumer/Handler
eb.consumer("com.makingdevs", { message ->
  message.reply("tu me dijiste ---${message.body()}---- y yo te respondo ---holi---")
  println "Mensaje recibido en el handler: "+message.body()
 })
//Verticle Sender
vertx.eventBus().send("com.makingdevs", "Hola", { reply ->
  if (reply.succeeded()) {
    println reply.result().body()
  }
})


router.route("/prueba1").handler { routingContext ->
      routingContext.response()
      .setStatusCode(201)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily([msg:"Numero: "+counter]))
counter++

}

server.requestHandler(router.&accept).listen(8080)
