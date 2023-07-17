package ru.hellforge.refcollector.service;

import java.util.List;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.TagDto;

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
}
