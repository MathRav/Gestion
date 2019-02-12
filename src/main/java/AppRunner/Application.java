package AppRunner;

import DAO.comptesTiersDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Controllers","DAO","Model","Security"} )
@EnableJpaRepositories(basePackages={"DAO"})
@EntityScan( basePackages = {"Model"} )
public class Application {
    @Value("${spring.thymeleaf.prefix}")
    static String appName;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(appName);
    }



}
