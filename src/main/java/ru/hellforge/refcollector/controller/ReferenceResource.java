package ru.hellforge.refcollector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.service.*;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.OK;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;
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

    private final TagService tagService;
    private final EnvironmentService environmentService;
    private final AccumulatesResponseService accumulatesResponseService;

    @GetMapping("/{referenceId}")
    public ResponseEntity<ReferenceDto> getReferenceById(@PathVariable(required = true) Long referenceId) {
        return ResponseEntity.status(OK).body(referenceService.getReferenceByIdList(referenceId));
    }

    @PostMapping
    public ResponseEntity<ReferenceDto> addReference(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto savedReferenceDto = referenceService.saveReference(referenceDto);

        savedReferenceDto.setTagObjectCodeList(referenceDto.getTagObjectCodeList());
        updateReferenceRelations(savedReferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReferenceDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ReferenceDto> updateReference(@RequestBody ReferenceDto referenceDto) {
        ReferenceDto updatedReferenceDto = referenceService.saveReference(referenceDto);

        updatedReferenceDto.setTagObjectCodeList(referenceDto.getTagObjectCodeList());
        updateReferenceRelations(updatedReferenceDto);

        return ResponseEntity.status(OK).body(updatedReferenceDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReference(@PathVariable(name = "id") Long id) {
        referenceService.deleteById(id);
        relationService.delete(REF_TAG, id);

        return ResponseEntity.noContent().build();
    }

    private void updateReferenceRelations(ReferenceDto reference) {
        if (!isIdValid(reference.getId()) || isNull(reference.getObjectCode()) || collectionIsEmpty(reference.getTagObjectCodeList()))
            return;

        relationService.saveListRelationFromReferenceDto(reference);
    }

    @GetMapping("/TEST")
    public void getReference(ReferenceFilterDto referenceFilterDto) {
        List<TagDto> tags = tagService.getAllTag(TagFilter.builder().build());
        List<ReferenceResponseDto> references = accumulatesResponseService.getReferenceResponse(referenceFilterDto);
        List<EnvironmentDto> environments = environmentService.getAllEnvironment();
    }

}