package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.JsonDataDto;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceResponseDto;
import ru.hellforge.refcollector.model.ExportProperties;

import java.util.List;

/**
 * AccumulatesResponseService.
 *
 * @author dprokofev
 */
public interface AccumulatesResponseService {

    List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList, ExportProperties properties);

    List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList);

    List<ReferenceResponseDto> getReferenceResponse(ReferenceFilterDto filter);

    JsonDataDto getExportDataDto(ExportProperties properties);
}
