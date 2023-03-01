package ru.hellforge.refcollector.service;

import java.util.List;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilter;

/**
 * ReferenceService.
 *
 * @author dprokofev
 */
public interface ReferenceService {

  List<ReferenceDto> getAllReference(ReferenceFilter filter);

  List<ReferenceDto> getReferenceByTag(String tag);

  ReferenceDto saveReference(ReferenceDto referenceDto);

  void deleteById(Long id);
}
