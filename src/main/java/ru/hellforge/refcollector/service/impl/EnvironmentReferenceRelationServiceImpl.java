package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.EnvironmentReferenceRelationDto;
import ru.hellforge.refcollector.mapper.EnvironmentReferenceMapper;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;
import ru.hellforge.refcollector.repository.EnvironmentReferenceRelationRepository;
import ru.hellforge.refcollector.service.EnvironmentReferenceRelationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvironmentReferenceRelationServiceImpl implements EnvironmentReferenceRelationService {

    private final EnvironmentReferenceRelationRepository environmentReferenceRelationRepository;
    private final EnvironmentReferenceMapper environmentReferenceMapper;

    @Override
    public void deleteRelation(Long relationId) {
        environmentReferenceRelationRepository.deleteById(relationId);
    }

    @Override
    public EnvironmentReferenceRelationDto addRelation(EnvironmentReferenceRelationDto relationDto) {
        EnvironmentReferenceRelation environmentReferenceRelation = environmentReferenceMapper.toEntity(relationDto);

        EnvironmentReferenceRelation savedRelation = environmentReferenceRelationRepository.save(environmentReferenceRelation);

        return environmentReferenceMapper.toDto(savedRelation);
    }

    @Override
    public List<Long> getReferenceIdListByEnvironmentId(Long environmentId) {
        return environmentReferenceRelationRepository.findAllByEnvironmentId(environmentId).stream()
                .map(EnvironmentReferenceRelation::getReferenceId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getReferenceIdListByEnvironmentId(List<Long> environmentIdList) {
        return environmentIdList.stream()
                .flatMap(environmentId -> getReferenceIdListByEnvironmentId(environmentId).stream())
                .collect(Collectors.toList());
    }

}