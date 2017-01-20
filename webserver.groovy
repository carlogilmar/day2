import io.vertx.groovy.ext.web.Router
import io.vertx.groovy.ext.web.handler.BodyHandler
import io.vertx.core.json.Json
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.handler.StaticHandler

//routers
def server = vertx.createHttpServer()
def router = Router.router(vertx)
router.route().handler(BodyHandler.create())

//Route to Index
router.route("/static/*").handler(
  StaticHandler.create().setCachingEnabled(false)
)

//Add new Email
router.route("/prueba").handler { routingContext ->
      routingContext.response()
      .setStatusCode(201)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily([msg:"Hola soy Vertx :D"]))
}

server.requestHandler(router.&accept).listen(8080)
