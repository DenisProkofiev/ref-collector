package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLData;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class DataController {

    @GetMapping("default_data")
    public SQLData getDefaultData() {
        return null;
    }

}
