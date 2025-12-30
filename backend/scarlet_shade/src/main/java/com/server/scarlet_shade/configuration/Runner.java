package com.server.scarlet_shade.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    
        System.out.println("Scalet Shade Server Running!");
    }    
}
