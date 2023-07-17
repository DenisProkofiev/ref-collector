package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceResponseDto;
import ru.hellforge.refcollector.model.ReferenceFilter;

import java.util.List;

/**
 * AccumulatesResponseService.
 *
 * @author dprokofev
 */
public interface AccumulatesResponseService {

    List<ReferenceResponseDto> getReferenceResponse(ReferenceFilterDto filter);

}
