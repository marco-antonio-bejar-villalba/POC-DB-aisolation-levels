package org.marco.poc.pocdbisolationlevels;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PocDbIsolationLevelsApplication {

    @PostConstruct
    public void init() {
      log.error("Application started");
    }

    public static void main(String[] args) {
        SpringApplication.run(PocDbIsolationLevelsApplication.class, args);
    }
}
