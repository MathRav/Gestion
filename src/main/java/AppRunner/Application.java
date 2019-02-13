package AppRunner;

import DAO.comptesTiersDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan(basePackages = {"Controllers","DAO","Model","Security"} )
@EnableJpaRepositories(basePackages={"DAO"})
@EntityScan( basePackages = {"Model"} )
@EnableTransactionManagement
public class Application {
    @Value("${spring.thymeleaf.prefix}")
    static String appName;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(appName);
    }

/*
    @Bean
      public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("Model");
//localContainerEntityManagerFactoryBean.setPersistenceUnitName("name");
        return factory;
      }

      @Bean
      public PlatformTransactionManager transactionManager(){
          JpaTransactionManager transactionManager  = new JpaTransactionManager();
          transactionManager.setEntityManagerFactory(
          getEntityManagerFactory().getObject() );
          return transactionManager;
      }
      */


}
