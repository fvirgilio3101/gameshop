package it.ecubit.gameshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class EcubitGameshopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcubitGameshopServiceApplication.class, args);
    }
}
