# Installation
```
resolvers += Resolver.bintrayRepo("morgaroth", "maven")
libraryDependencies += "io.morgaroth" %% "op-rabbit-rpc" % "1.0.0"
```

# Usage

### Server

```tut
import com.spingo.op_rabbit.Directives._
import io.morgaroth.oprabbit.config.QueueConfig
import io.morgaroth.oprabbit.server.BaseRpcServer
import io.morgaroth.oprabbit.RabbitControlActor

// define queue configuration
val queueCfg = QueueConfig("qname", "", 5, List("*"))

// define handlers
val handleSimpleTaskHandler =
  routingKey {
    case "simple-task" =>
      println("simple task handled")
      ack()
  }

val debugHandler =
  routingKey { rk =>
    body(as[String]) { content =>
      println(s"$rk: $content")
      ack()
    }
  }

// set up server
val server = new BaseRpcServer(queueCfg, List(handleSimpleTaskHandler, debugHandler))

// prepare environment
implicit val rc = RabbitControlActor.deadLetters
server.subscribe()
```