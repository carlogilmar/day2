import io.vertx.groovy.ext.web.Router
import io.vertx.groovy.ext.web.handler.BodyHandler
import io.vertx.core.json.Json
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.handler.StaticHandler

//Event Bus
def eb = vertx.eventBus()

//Verticle Sender
vertx.eventBus().send("com.makingdevs", 1, { reply ->
  if (reply.succeeded()) {
    println reply.result().body()
  }
})


