package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@RestController
@RequestMapping("/api/demo")

public class Main {
    public static void main(String[] args) throws  IOException {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi front-enders from secured endpoint");
    }

}