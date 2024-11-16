package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.enums.EntityType;
import ru.hellforge.refcollector.enums.RelationType;

import java.util.List;
import java.util.UUID;

public interface RelationService {
    RelationDto addRelation(RelationDto relation);

    List<RelationDto> saveListRelationFromReferenceDto(ReferenceDto referenceDto);

    List<RelationImportDto> getAllImportRelation();

    List<RelationImportDto> importRelation(List<RelationImportDto> relations);

    List<Long> getReferenceIdListByTagId(Long tagId);

    List<RelationDto> getTagIdListByReferenceIdList(List<UUID> objectCodeList);

    List<RelationDto> getFiltredReferenceIdList(ReferenceFilterDto filter);

    void delete(RelationType type, Long id);
    void deleteByTypeAndObjectCode(RelationType type, EntityType entityType, UUID objectCode);

    void delete(Long id);

    List<RelationDto> getRelationListFromReferenceObjectCodeList(List<UUID> referenceObjectCodeList);
}
