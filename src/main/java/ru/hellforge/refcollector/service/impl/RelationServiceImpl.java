package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.RelationDto;
import ru.hellforge.refcollector.dto.RelationImportDto;
import ru.hellforge.refcollector.enums.RelationType;
import ru.hellforge.refcollector.mapper.RelationMapper;
import ru.hellforge.refcollector.model.entity.Relation;
import ru.hellforge.refcollector.repository.RelationRepository;
import ru.hellforge.refcollector.service.RelationService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.hellforge.refcollector.enums.EntityType.REFERENCE;
import static ru.hellforge.refcollector.enums.RelationType.REF_ENV;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;
import static ru.hellforge.refcollector.util.BaseOperation.collectionNotEmpty;

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
        return relationRepository.findAllByTagIdAndType(tagId, REF_TAG.name());
    }

    @Override
    public List<RelationDto> getTagIdListByReferenceIdList(List<Long> idList) {
        return relationMapper.toDtoList(relationRepository.findAllByReferenceIdIn(idList));
    }

    @Override
    public void delete(RelationType type, Long id) {
        if (REFERENCE.equals(type)) {
            relationRepository.deleteAllByReferenceId(id);
        }
    }

    private List<Relation> getListRelationsFromReference(ReferenceDto referenceDto) {
        List<Relation> relations = new ArrayList<>();

        if (collectionNotEmpty(referenceDto.getTagIdList())) {
            for (Long tagId : referenceDto.getTagIdList()) {
                relations.add(getRelation(referenceDto.getId(), tagId, REF_TAG));
            }
        }
        if (nonNull(referenceDto.getEnvironmentId())) {
            relations.add(getRelation(referenceDto.getId(), referenceDto.getEnvironmentId(), REF_ENV));
        }

        return relations;
    }

    private Relation getRelation(Long referenceId, Long id, RelationType type) {
        Relation relation = new Relation();
        relation.setReferenceId(referenceId);

        if (type.equals(REF_TAG))
            relation.setTagId(id);
        else if (type.equals(REF_ENV))
            relation.setEnvironmentId(id);

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