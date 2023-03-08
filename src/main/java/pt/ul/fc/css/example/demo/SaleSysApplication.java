package pt.ul.fc.css.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SaleSysApplication {

  private static final Logger log = LoggerFactory.getLogger(SaleSysApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SaleSysApplication.class, args);
  }

  @Bean
  public CommandLineRunner salesys() {
    return args -> {
      log.info("Starting application-specific loading...");

      log.info("Loaded!");
    };
  }
}
