package AppRunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Controllers","DAO","Model","Security"} )
public class Application {
    @Value("${spring.thymeleaf.prefix}")
    static String appName;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(appName);
    }

}
