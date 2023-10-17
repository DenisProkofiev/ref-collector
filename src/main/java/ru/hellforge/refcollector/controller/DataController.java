package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.DataService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {
    private final DataService dataService;

    @PostMapping("/export")
    public void exportDump(@RequestBody ExportProperties properties) throws IOException {
        dataService.exportDumpToFile(properties);
    }

    @GetMapping("/import")
    public void importDump(@RequestParam String source) throws IOException {
        dataService.importDataFromFile(source);
    }

}