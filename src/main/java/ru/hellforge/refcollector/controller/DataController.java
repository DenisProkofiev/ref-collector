package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.AccumulatesResponseService;
import ru.hellforge.refcollector.service.DataService;

import java.io.IOException;
import java.sql.SQLData;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {
    private final AccumulatesResponseService accumulatesResponseService;
    private final DataService dataService;
//    @GetMapping()
//    public ResponseEntity<List<ReferenceDto>> getDefaultData(@RequestParam List<Long> environmentIdList) {
//        return ResponseEntity.status(OK).body(accumulatesResponseService.getReferenceDtoListByReferenceIdList(environmentIdList, properties));
//    }

    @PostMapping("/export")
    public void exportDump(@RequestBody ExportProperties properties) throws IOException {
        dataService.saveJsonToFile(accumulatesResponseService.getReferenceDtoListByReferenceIdList(List.of(21L, 22L)),properties);
    }

    @GetMapping("/import")
    public void importDump(@RequestBody String source) throws IOException {
        dataService.importDataFromFile(source);
    }

}