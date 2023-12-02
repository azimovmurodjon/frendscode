package com.frendscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class Main {
    //Our fake DB

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //    @RequestMapping(
//            path = "api/v1/customers",
//            method = RequestMethod.GET
//    )



}
