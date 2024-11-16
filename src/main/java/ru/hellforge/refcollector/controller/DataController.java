package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.h2.util.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.DataService;

import java.io.IOException;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {
    private final DataService dataService;

    @PostMapping("/export")
    public void exportDump(@RequestBody ExportProperties properties) throws IOException {
        dataService.exportDumpToFile(properties);
    }

    @PostMapping(value = "/import")
    public ResponseEntity<JsonDataDto> importDump(@RequestBody JSONObject json) throws IOException {
        return ResponseEntity.status(ACCEPTED).body(dataService.importDataFromFile(json));
    }

}