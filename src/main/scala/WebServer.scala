import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http

import scala.concurrent.duration._
import scala.io.StdIn

object WebServer {

  def main(args: Array[String]) {

    val config = ConfigFactory.load()
    val host = config.getString("http.host")
    val port = config.getInt("http.port")

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    implicit val executionContext = system.dispatcher
    implicit val timeout = Timeout(10 seconds)

    val route =
      getAllItems ~
      getItemById


    val bindingFuture = Http().bindAndHandle(route, host, port)

    println(s"Server online at $host:$port/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ ⇒ system.terminate())


  }

  val getAllItems = path("items") {
    get {
      complete{
        val content = "All items will be displayed here"
        HttpEntity(ContentTypes.`text/html(UTF-8)`, content)
      }
    }
  }

  val getItemById = path("items" / JavaUUID){ id =>
    get {
      complete{
        val content = "The will get the item associated with this id"
        HttpEntity(ContentTypes.`text/html(UTF-8)`, content)
      }
    }
  }

}
