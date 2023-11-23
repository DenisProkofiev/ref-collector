package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.enums.RelationType;

import java.util.List;

public interface RelationService {
    RelationDto addRelation(RelationDto relation);

    List<RelationDto> saveListRelationFromReferenceDto(ReferenceDto referenceDto);

    List<RelationImportDto> getAllImportRelation();

    List<RelationImportDto> importRelation(List<RelationImportDto> relations);

    List<Long> getReferenceIdListByTagId(Long tagId);

    List<RelationDto> getTagIdListByReferenceIdList(List<Long> idList);

    void delete(RelationType type, Long id);
}
