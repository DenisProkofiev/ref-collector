package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.BaseRelationDto;
import ru.hellforge.refcollector.dto.BaseRelationImportDto;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.enums.RelationType;
import ru.hellforge.refcollector.mapper.BaseReferenceMapper;
import ru.hellforge.refcollector.model.entity.BaseRelation;
import ru.hellforge.refcollector.repository.BaseRelationRepository;
import ru.hellforge.refcollector.service.BaseRelationService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;
import static ru.hellforge.refcollector.enums.RelationType.REF_ENV;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;

@Service
@RequiredArgsConstructor
public class BaseRelationServiceImpl implements BaseRelationService {
    private final BaseRelationRepository baseRelationRepository;
    private final BaseReferenceMapper baseReferenceMapper;

    @Override
    public BaseRelationDto addRelation(BaseRelationDto relation) {
        BaseRelation savedRelation = baseRelationRepository.save(baseReferenceMapper.toEntity(relation));
        return baseReferenceMapper.toDto(savedRelation);
    }

    @Override
    public List<BaseRelationDto> saveListRelationFromReferenceDto(ReferenceDto referenceDto) {
        List<BaseRelation> newRelations = getListBaseRelationsFromReference(referenceDto);

        return baseReferenceMapper.toDtoList(baseRelationRepository.saveAll(newRelations));
    }

    @Override
    public List<BaseRelationImportDto> getAllImportBaseRelation() {
        return baseReferenceMapper.entityListToImportDtoList(baseRelationRepository.findAll());
    }

    private List<BaseRelation> getListBaseRelationsFromReference(ReferenceDto referenceDto) {
        List<BaseRelation> relations = new ArrayList<>();

        if (!isEmpty(referenceDto.getTagIdList())) {
            for (Long tagId : referenceDto.getTagIdList()) {
                relations.add(getBaseRelation(referenceDto.getId(), tagId, REF_TAG));
            }
        }

        if (nonNull(referenceDto.getEnvironmentId())) {
            relations.add(getBaseRelation(referenceDto.getId(), referenceDto.getEnvironmentId(), REF_ENV));
        }

        return relations;
    }

    private BaseRelation getBaseRelation(Long referenceId, Long id, RelationType type) {
        BaseRelation relation = new BaseRelation();
        relation.setReferenceId(referenceId);

        if (type.equals(REF_TAG))
            relation.setTagId(id);
        else if (type.equals(REF_ENV))
            relation.setEnvironmentId(id);

        return relation;
    }

}