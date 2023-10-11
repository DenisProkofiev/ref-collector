package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;

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

    void importReferenceList(List<ReferenceImportDto> referenceDtoList);

    void deleteById(Long id);

    ReferenceDto getReferenceById(Long referenceId);

    List<ReferenceDto> getReferenceById(List<Long> referenceIdList);

    List<ReferenceImportDto> getAllImportReference();
}