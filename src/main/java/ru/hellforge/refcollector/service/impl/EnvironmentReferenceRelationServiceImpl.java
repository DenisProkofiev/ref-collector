package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.EnvironmentReferenceRelationDto;
import ru.hellforge.refcollector.mapper.EnvironmentReferenceMapper;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;
import ru.hellforge.refcollector.repository.EnvironmentReferenceRelationRepository;
import ru.hellforge.refcollector.service.EnvironmentReferenceRelationService;
import ru.hellforge.refcollector.service.EnvironmentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnvironmentReferenceRelationServiceImpl implements EnvironmentReferenceRelationService {

    private final EnvironmentService environmentService;
    private final EnvironmentReferenceRelationRepository environmentReferenceRelationRepository;
    private final EnvironmentReferenceMapper environmentReferenceMapper;

    @Override
    public void addReferenceToEnvironment(Long environmentId, Long referenceId) {
        if (environmentService.isEnvironmentExist(environmentId)) {
            EnvironmentReferenceRelation relation = new EnvironmentReferenceRelation();
            relation.setEnvironmentId(environmentId);
            relation.setReferenceId(referenceId);

            environmentReferenceRelationRepository.save(relation);
        } else
            throw new EntityNotFoundException();
    }

    @Override
    public EnvironmentReferenceRelation getEnvironmentReferenceRelationByID(Long environmentId) {
           return environmentReferenceRelationRepository.findById(environmentId).orElseThrow(EntityNotFoundException::new);

    }
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
                .collect(toList());
    }

    @Override
    public List<Long> getReferenceIdListByEnvironmentIdList(List<Long> environmentIdList) {
        return environmentReferenceRelationRepository.findAllByEnvironmentIdIn(environmentIdList);
    }

    @Override
    public List<EnvironmentReferenceRelationDto> getAllRelations() {
        return environmentReferenceRelationRepository.findAll().stream()
                .map(environmentReferenceMapper::toDto)
                .collect(toList());
    }

}