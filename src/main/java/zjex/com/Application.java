package zjex.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.petstore.controller.PetController;

/**
 * Created by frere921 on 2017/3/23.
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = {SwaggerConfig.class,PetController.class})
public class Application {

    public static void main(String[] args) {
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    }
