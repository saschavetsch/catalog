package ch.fhnw.catalog.loadtests;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

// Run the tests with: mvn gatling:test
// Order application needs to be running for the tests to work.
// Application can be started normally with docker-compose up --build.
public class CatalogLoadTests extends Simulation {
    HttpProtocolBuilder httpProtocol = 
        http.baseUrl("http://localhost:8080")
        .acceptHeader("application/json");

    ScenarioBuilder searchScenario = scenario("100 Search Requests")
        .exec(http("Get Catalog Items")
        .get("/books?title=Sapiens")
        .check(status().is(200))); 

    {
        setUp(
            searchScenario.injectOpen(rampUsers(100).during(10))
        ).protocols(httpProtocol);
    }
}
