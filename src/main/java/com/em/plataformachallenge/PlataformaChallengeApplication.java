package com.em.plataformachallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Validador de precios", version = "1.0",
    contact = @Contact(name = "Ezequiel Morales", email = "ezequielmorales5@icloud.com",
        url = "https://www.linkedin.com/in/ezequiel-morales-a9438b19b/")))
public class PlataformaChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlataformaChallengeApplication.class, args);
  }

}
