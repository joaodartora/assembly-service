package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AssemblySimulation extends Simulation {

  val csvFeeder = csv("src/test/scala/computerdatabase/associateds.csv").circular

  val httpProtocol = http
    .baseUrl("http://localhost:8080/v1")
    .acceptHeader("application/json")

  val vote = scenario("Associated votes processing")
    .feed(csvFeeder)
    .exec(http("Vote")
      .post("/votes/agenda/1")
      .body(StringBody(
        """{
    "associated": {
      "cpf": ${cpf},
      "id": ${cpf}
    },
    "vote": "NO"
  }""".stripMargin)).asJson
      .check(status.is(200)))

  setUp(vote.inject(atOnceUsers(100))
    .protocols(httpProtocol))
}
