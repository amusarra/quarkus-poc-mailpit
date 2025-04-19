package it.dontesta.labs.quarkus.mailpit;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/send")
public class MailResource {

  @Inject
  Mailer mailer;

  @GET
  public String sendEmail() {
    mailer.send(
        Mail.withText("test@example.com", "Ciao da Quarkus!", "Questa è una email di test."));
    return "Email inviata!";
  }
}
