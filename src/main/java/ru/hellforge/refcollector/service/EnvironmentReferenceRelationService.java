package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.EnvironmentReferenceRelationDto;

import java.util.List;

public interface EnvironmentReferenceRelationService {

    void deleteRelation(Long relationId);

    EnvironmentReferenceRelationDto addRelation(EnvironmentReferenceRelationDto relationDto);

    List<Long> getReferenceIdListByEnvironmentId(Long environmentId);

    List<Long> getReferenceIdListByEnvironmentId(List<Long> environmentIdList);

}
