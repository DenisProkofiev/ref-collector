package ru.hellforge.refcollector;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RefcollectorApplication {

    public static void main(String[] args) {

        SpringApplication.run(RefcollectorApplication.class);
    }
}
