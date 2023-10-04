package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.EnvironmentReferenceRelationDto;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;

import java.util.List;

public interface EnvironmentReferenceRelationService {

    EnvironmentReferenceRelation getEnvironmentReferenceRelationByID(Long environmentId);

    void deleteRelation(Long relationId);

    EnvironmentReferenceRelationDto addRelation(EnvironmentReferenceRelationDto relationDto);

    List<Long> getReferenceIdListByEnvironmentId(Long environmentId);

    List<Long> getReferenceIdListByEnvironmentIdList(List<Long> environmentIdList);

    void addReferenceToEnvironment(Long environmentId, Long referenceId);

    List<EnvironmentReferenceRelationDto> getAllRelations();

}