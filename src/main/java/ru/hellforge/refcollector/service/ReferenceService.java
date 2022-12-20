package ru.hellforge.refcollector.service;

import java.util.List;
import ru.hellforge.refcollector.dto.ReferenceDto;

/**
 * ReferenceService.
 *
 * @author dprokofev
 */
public interface ReferenceService {

  List<ReferenceDto> getReference();

  ReferenceDto saveReference(ReferenceDto referenceDto);
}
