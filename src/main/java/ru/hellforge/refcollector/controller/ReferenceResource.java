package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.RelationService;

import java.util.UUID;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.OK;
import static ru.hellforge.refcollector.util.BaseOperationService.collectionIsEmpty;
import static ru.hellforge.refcollector.util.BaseOperationService.isIdValid;

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
    private final RelationService relationService;

    @GetMapping("/id/{referenceId}")
    public ResponseEntity<ReferenceDto> getById(@PathVariable(required = true) Long referenceId) {
        return ResponseEntity.status(OK).body(referenceService.getReferenceById(referenceId));
    }

    @GetMapping("/object-code/{objectCode}")
    public ResponseEntity<ReferenceDto> getByObjectCode(@PathVariable(required = true) UUID objectCode) {
        return ResponseEntity.status(OK).body(referenceService.getReferenceByObjectCode(objectCode));
    }

    @PostMapping
    public ResponseEntity<ReferenceDto> create(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto savedReferenceDto = referenceService.saveReference(referenceDto);

        savedReferenceDto.setTagObjectCodeList(referenceDto.getTagObjectCodeList());
        updateReferenceRelations(savedReferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReferenceDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ReferenceDto> update(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto updatedReferenceDto = referenceService.saveReference(referenceDto);

        updatedReferenceDto.setTagObjectCodeList(referenceDto.getTagObjectCodeList());
        updateReferenceRelations(updatedReferenceDto);

        return ResponseEntity.status(OK).body(updatedReferenceDto);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long referenceId) {
        referenceService.deleteById(referenceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/object-code/{objectCode}")
    public ResponseEntity<Void> delete(@PathVariable(name = "objectCode") UUID objectCode) {
        referenceService.deleteByObjectCode(objectCode);
        return ResponseEntity.noContent().build();
    }

    private void updateReferenceRelations(ReferenceDto reference) {
        if (!isIdValid(reference.getId()) || isNull(reference.getObjectCode()) || collectionIsEmpty(reference.getTagObjectCodeList()))
            return;

        relationService.saveListRelationFromReferenceDto(reference);
    }

    @GetMapping("/TEST")
    public void getReference(ReferenceFilterDto referenceFilterDto) {
//        List<TagDto> tags = tagService.getAllTag(TagFilter.builder().build());
//        List<ReferenceResponseDto> references = accumulatesResponseService.getReferenceResponse(referenceFilterDto);
//        List<EnvironmentDto> environments = environmentService.getAllEnvironment();
    }

}