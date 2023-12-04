package com.frendscode;

import com.frendscode.Customer.Customer;
import com.frendscode.Customer.CustomerRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
public class Main {
    //Our fake DB

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Main.class, args);
//        printBeans(applicationContext);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Foo getFoo() {
        return new Foo("bar");
    }

    record Foo(String name) {
    }

    private static void printBeans(ConfigurableApplicationContext ctx) {
        String[] beanDefinitionNames =
                ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {

        return args -> {
            Customer alex = new Customer(
                    "Alex",
                    "alex@gmail.com",
                    21
            );

            Customer jamila = new Customer(
                    "Jamila",
                    "jamila@gmail.com",
                    20
            );

            Customer iryna = new Customer(
                    "Iryna",
                    "iryna@gmail.com",
                    19
            );
            List<Customer> customers = List.of(alex, jamila);
            customerRepository.saveAll(customers);
        };
    }

}
