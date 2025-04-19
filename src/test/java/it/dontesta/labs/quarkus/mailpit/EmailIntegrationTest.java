package it.dontesta.labs.quarkus.mailpit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.quarkiverse.mailpit.test.InjectMailbox;
import io.quarkiverse.mailpit.test.Mailbox;
import io.quarkiverse.mailpit.test.WithMailbox;
import io.quarkiverse.mailpit.test.invoker.ApiException;
import io.quarkiverse.mailpit.test.model.Message;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@WithMailbox
public class EmailIntegrationTest {

  @InjectMailbox
  Mailbox mailbox;

  @AfterEach
  public void afterEach() throws ApiException {
    // clear the mailbox after each test run if you prefer
    mailbox.clear();
  }

  @Test
  void shouldSendEmailAndVerifyInMailpit() throws InterruptedException {
    // Step 1: Chiamata all'endpoint che invia l'email
    RestAssured.get("/send").then().statusCode(200);

    // Attendi che Mailpit riceva l'email
    Thread.sleep(1000);

    // look up the mail and assert it
    Message message = mailbox.findFirst("no-reply@example.com");
    assertThat(message, notNullValue());
    assertThat(message.getTo().get(0).getAddress(), is("test@example.com"));
    assertThat(message.getSubject(), is("Ciao da Quarkus!"));
    assertThat(message.getText(), containsString("email di test"));
  }
}
