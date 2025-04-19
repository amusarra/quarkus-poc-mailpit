package it.dontesta.labs.quarkus.mailpit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class EmailIntegrationTest {

  @Test
  void shouldSendEmailAndVerifyInMailpit() throws InterruptedException {
    // Step 1: Chiamata all'endpoint che invia l'email
    RestAssured.get("/send").then().statusCode(200);

    // Attendi che Mailpit riceva l'email
    Thread.sleep(1000);

    // Step 2: Recupera l'ultima email da Mailpit
    var response = RestAssured
        .get("http://localhost:8025/api/v1/messages")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath();

    var subject = response.getString("messages[0].Subject");
    var body = response.getString("messages[0].Snippet");

    assertThat(subject, containsString("Ciao da Quarkus"));
    assertThat(body, containsString("email di test"));
  }
}
