package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.service.EnvironmentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/environment")
public class EnvironmentResource {

    private final EnvironmentService environmentService;

    @GetMapping
    public ResponseEntity<List<EnvironmentDto>> getAllEnvironment() {
        return ResponseEntity.status(OK).body(environmentService.getAllEnvironment());
    }

    @PostMapping
    public ResponseEntity<EnvironmentDto> addEnvironment(@RequestBody EnvironmentDto environmentDto) {
        return ResponseEntity.status(CREATED).body(environmentService.addEnvironment(environmentDto));
    }

}