package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hellforge.refcollector.service.RelationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/relation")
public class RelationResource {
    private final RelationService relationService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        relationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
