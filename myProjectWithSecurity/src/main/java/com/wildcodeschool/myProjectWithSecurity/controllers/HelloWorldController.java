package com.wildcodeschool.myProjectWithSecurity.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    public static List<String> SECRET_BASES = Arrays.asList(
            "Biarritz",
            "Bordeaux",
            "La Loupe 🌲",
            "Lille",
            "Lyon",
            "Nantes",
            "Orléans",
            "Paris",
            "Reims",
            "Strasbourg",
            "Toulouse",
            "Amsterdam",
            "Barcelone",
            "Berlin",
            "Lisbonne",
            "Madrid" );

    @GetMapping("/")
    public String hello() {
        return "Welcome to the SHIELD";
    }

    @GetMapping("/secret-bases")
    public List<String> admin() {
        return SECRET_BASES;
    }

    @GetMapping("/avengers/assemble")
    public String user() {
        return "Avengers... Assemble";
    }

    @GetMapping("/access-denied")
    public String accesDenied() {
        return "GO AWAY!!!";
    }
}
