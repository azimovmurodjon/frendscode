package com.frendscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class Main {
    //Our fake DB

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Main.class, args);

        String[] beanDefinitionNames =
                applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    //    @RequestMapping(
//            path = "api/v1/customers",
//            method = RequestMethod.GET
//    )


}
