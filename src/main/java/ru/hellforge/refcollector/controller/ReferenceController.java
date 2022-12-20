package ru.hellforge.refcollector.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.service.ReferenceService;

/**
 * ReferenceController.
 *
 * @author dprokofev
 */
@RestController
@RequiredArgsConstructor
public class ReferenceController {

  private final ReferenceService referenceService;

  @GetMapping("/reference")
  public ResponseEntity<List<ReferenceDto>> getReference() {
    return ResponseEntity.status(HttpStatus.OK).body(referenceService.getReference());
  }

  @PostMapping("/reference")
  public ResponseEntity<ReferenceDto> saveReference(ReferenceDto referenceDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(referenceService.saveReference(referenceDto));
  }

}
