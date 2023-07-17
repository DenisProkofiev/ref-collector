package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.service.TagService;

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

    return ResponseEntity.status(HttpStatus.OK).body(tag);
  }

  @PostMapping
  public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) {
    TagDto savedTagDto = tagService.saveTag(tagDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedTagDto);
  }

}