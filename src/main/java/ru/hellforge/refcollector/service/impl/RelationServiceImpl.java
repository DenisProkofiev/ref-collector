package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.enums.EntityType;
import ru.hellforge.refcollector.enums.RelationType;
import ru.hellforge.refcollector.mapper.RelationMapper;
import ru.hellforge.refcollector.model.entity.Relation;
import ru.hellforge.refcollector.repository.RelationRepository;
import ru.hellforge.refcollector.repository.specification.RelationSpecification;
import ru.hellforge.refcollector.service.RelationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static ru.hellforge.refcollector.enums.EntityType.REFERENCE;
import static ru.hellforge.refcollector.enums.RelationType.REF_ENV;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;
import static ru.hellforge.refcollector.util.BaseOperationService.collectionNotEmpty;
import static ru.hellforge.refcollector.util.BaseOperationService.convertListUUIDToStringList;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    private final RelationRepository relationRepository;
    private final RelationMapper relationMapper;

    @Override
    public RelationDto addRelation(RelationDto relationDto) {
        Relation savedRelation = relationRepository.save(relationMapper.toEntity(relationDto));
        return relationMapper.toDto(savedRelation);
    }

    @Override
    public List<RelationDto> saveListRelationFromReferenceDto(ReferenceDto referenceDto) {
        List<Relation> newRelations = getListRelationsFromReference(referenceDto);

        return relationMapper.toDtoList(relationRepository.saveAll(newRelations));
    }

    @Override
    public List<RelationImportDto> getAllImportRelation() {
        return relationMapper.entityListToImportDtoList(relationRepository.findAll());
    }

    @Override
    public List<RelationImportDto> importRelation(List<RelationImportDto> relationImportDtoList) {
        return null;
    }

    @Override
    public List<Long> getReferenceIdListByTagId(Long tagId) {
        return relationRepository.findAllByTagIdAndType(tagId, REF_TAG.name()).stream()
                .map(Relation::getReferenceId)
                .collect(toList());
    }

    @Override
    public List<RelationDto> getTagIdListByReferenceIdList(List<UUID> objectCodeList) {
        List<Relation> relations = relationRepository.findAll().stream()
                .filter(relation -> objectCodeList.toString().contains(relation.getReferenceObjectCode().toString()))
                .collect(toList());

        return relationMapper.toDtoList(relations);
    }

    @Override
    public List<RelationDto> getFiltredReferenceIdList(ReferenceFilterDto filter) {
        List<Relation> relations = relationRepository.findAll(RelationSpecification.getSpecification(filter));

        return relationMapper.toDtoList(relations);
    }

    @Override
    public void delete(RelationType type, Long referenceId) {
        if (REFERENCE.equals(type)) {
            relationRepository.deleteAllByReferenceId(referenceId);
        }
    }

    @Override
    public void deleteByTypeAndObjectCode(RelationType type, EntityType entityType, UUID objectCode) {
        switch (entityType) {
            case REFERENCE: relationRepository.deleteAllByTypeAndReferenceObjectCode(type.toString(), objectCode.toString());
            case TAG: relationRepository.deleteAllByTypeAndTagObjectCode(type.toString(), objectCode.toString());
        }
    }

    @Override
    public void delete(Long id) {
        relationRepository.deleteById(id);
    }

    @Override
    public List<RelationDto> getRelationListFromReferenceObjectCodeList(List<UUID> referenceObjectCodeList) {
        List<Relation> relations = relationRepository.findAll().stream()
                .filter(relation -> convertListUUIDToStringList(referenceObjectCodeList).contains(relation.getReferenceObjectCode()))
                .collect(toList());
        return relationMapper.toDtoList(relations);
    }

    private List<Relation> getListRelationsFromReference(ReferenceDto referenceDto) {
        List<Relation> relations = new ArrayList<>();

        if (collectionNotEmpty(referenceDto.getTagObjectCodeList())) {
            for (UUID objectCode : referenceDto.getTagObjectCodeList()) {
                relations.add(getRelation(referenceDto.getObjectCode(), objectCode, REF_TAG));
            }
        }
        if (nonNull(referenceDto.getEnvironmentId())) {
            relations.add(getRelation(referenceDto.getObjectCode(), referenceDto.getEnvironmentObjectCode(), REF_ENV));
        }

        return relations;
    }

    private Relation getRelation(UUID referenceObjectCode, UUID objectCode, RelationType type) {
        Relation relation = new Relation();
        relation.setReferenceObjectCode(referenceObjectCode.toString());

        if (type.equals(REF_TAG))
            relation.setTagObjectCode(objectCode.toString());
        else if (type.equals(REF_ENV))
            relation.setEnvironmentObjectCode(objectCode.toString());

        relation.setType(type.name());

        return relation;
    }

    private List<RelationImportDto> compareImportRelation(List<RelationImportDto> baseRelationImportDtoList) {
        List<Relation> baseRelations = relationRepository.findAll();

        List<RelationImportDto> baseRelationImportDtos = new ArrayList<>();

        for (RelationImportDto baseRelationImportDto : baseRelationImportDtoList) {
            for (Relation baseRelation : baseRelations) {
                if (compareTwoFields(baseRelationImportDto.getTagId(), baseRelation.getTagId())
                    && compareTwoFields(baseRelationImportDto.getReferenceId(), baseRelation.getReferenceId())
                    && compareTwoFields(baseRelationImportDto.getEnvironmentId(), baseRelation.getEnvironmentId())
                    && compareTwoFields(baseRelationImportDto.getType(), baseRelation.getType())) {
                    baseRelationImportDtos.add(baseRelationImportDto);
                }
            }
        }
        return baseRelationImportDtos;
    }

    private boolean compareTwoFields(Object first, Object second) {
        if (nonNull(first) && nonNull(second)) {
            return first.equals(second);
        }
        return isNull(first) && isNull(second);
    }

}