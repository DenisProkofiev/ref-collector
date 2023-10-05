package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.BaseRelationDto;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.model.entity.BaseRelation;

import java.util.List;

public interface BaseRelationService {
    BaseRelationDto addRelation(BaseRelationDto relation);

    List<BaseRelationDto> getListRelationFromReferenceDto(ReferenceDto referenceDto);
}
