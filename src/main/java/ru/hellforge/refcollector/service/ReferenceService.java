package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.enums.EntityType;

import java.util.List;

/**
 * ReferenceService.
 *
 * @author dprokofev
 */
public interface ReferenceService {

    List<ReferenceDto> getAllReference(ReferenceFilterDto filter);

    ReferenceDto saveReference(ReferenceDto referenceDto);

    List<ReferenceDto> saveReferenceList(List<ReferenceDto> referenceDtoList);

    List<ReferenceImportDto> importReference(List<ReferenceImportDto> referenceDtoList);

    void deleteById(Long id);

    ReferenceDto getReferenceByIdList(Long referenceId);

    List<ReferenceDto> getReferenceByIdList(List<Long> referenceIdList);

    List<ReferenceImportDto> getAllImportReference();
}