package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.service.EnvironmentReferenceRelationService;
import ru.hellforge.refcollector.service.EnvironmentService;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.CollectionUtils.isEmpty;
import static ru.hellforge.refcollector.util.BaseOperation.isIdValid;

/**
 * ReferenceResource.
 *
 * @author dprokofev
 */
@RestController
@RequestMapping("/api/v1/reference")
@RequiredArgsConstructor
public class ReferenceResource {

    private final ReferenceService referenceService;
    private final ReferenceTagRelationService referenceTagRelationService;
    private final EnvironmentReferenceRelationService environmentReferenceRelationService;

    @GetMapping("/{referenceId}")
    public ResponseEntity<ReferenceDto> getReferenceById(@PathVariable(required = true) Long referenceId) {
        return  ResponseEntity.status(OK).body(referenceService.getReferenceById(referenceId));
    }

    @PostMapping
    public ResponseEntity<ReferenceDto> addReference(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto savedReference = referenceService.saveReference(referenceDto);

        if(isIdValid(referenceDto.getEnvironmentId())) {
            environmentReferenceRelationService.addReferenceToEnvironment(referenceDto.getId(), referenceDto.getEnvironmentId());
        }

        if (nonNull(savedReference.getId()) && !isEmpty(referenceDto.getTagIdList())) {
            savedReference.setTagIdList(referenceDto.getTagIdList());
            referenceTagRelationService.addRelationFromReference(savedReference);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReference);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ReferenceDto> updateReference(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto updatedReference = referenceService.saveReference(referenceDto);
        return ResponseEntity.status(OK).body(updatedReference);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReference(@PathVariable(name = "id") Long id) {
        referenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}