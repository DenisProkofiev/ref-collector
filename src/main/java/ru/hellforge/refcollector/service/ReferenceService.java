package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;

import java.util.List;

/**
 * ReferenceService.
 *
 * @author dprokofev
 */
public interface ReferenceService {

    List<ReferenceDto> getAllReference(ReferenceFilterDto filter);

    ReferenceDto saveReference(ReferenceDto referenceDto);

    void deleteById(Long id);

    ReferenceDto getReferenceById(Long referenceId);

    List<ReferenceDto> getReferenceById(List<Long> referenceIdList);
}