package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;

import java.util.List;
import java.util.UUID;

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
    void deleteByObjectCode(UUID objectCode);

    ReferenceDto getReferenceById(Long referenceId);

    List<ReferenceDto> getReferenceByIdList(List<Long> referenceIdList);

    List<ReferenceImportDto> getAllImportReference();

    ReferenceDto getReferenceByObjectCode(UUID objectCode);
}