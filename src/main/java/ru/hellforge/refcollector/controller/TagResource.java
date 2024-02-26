package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.service.TagService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

/**
 * TagResource.
 *
 * @author dprokofev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
public class TagResource {
    private final TagService tagService;

    @GetMapping("/{tagId}")
    public ResponseEntity<TagDto> getById(@PathVariable Long tagId) {
        TagDto tag = tagService.getById(tagId);

        return ResponseEntity.status(OK).body(tag);
    }

    @PostMapping
    public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) {
        TagDto savedTagDto = tagService.saveTag(tagDto);

        return ResponseEntity.status(CREATED).body(savedTagDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TagDto> update(@RequestBody TagDto tagDto) {
        TagDto updatedTag = tagService.saveTag(tagDto);
        return ResponseEntity.status(OK).body(updatedTag);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        tagService.deleteById(id);
        return  ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{objectCode}")
    public ResponseEntity<Void> delete(@PathVariable(name = "objectCode") UUID objectCode) {
        tagService.deleteByObjectCode(objectCode);
        return  ResponseEntity.status(NO_CONTENT).build();
    }

}