package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.mapper.EnvironmentMapper;
import ru.hellforge.refcollector.model.entity.Environment;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;
import ru.hellforge.refcollector.repository.EnvironmentRepository;
import ru.hellforge.refcollector.service.EnvironmentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final EnvironmentMapper environmentMapper;

    @Override
    public List<EnvironmentDto> getAllEnvironment() {
        List<Environment> allEnvironmentList = environmentRepository.findAll();

        return environmentMapper.toDtoList(allEnvironmentList);
    }

    @Override
    public List<EnvironmentDto> getAllEnvironment(ReferenceFilterDto filter) {
        return Objects.isNull(filter) || Objects.isNull(filter.getEnvironmentId()) ? getAllEnvironment()
                : List.of(getEnvironmentById(filter.getEnvironmentId()));
    }

    @Override
    public List<Long> getAllEnvironmentId(ReferenceFilterDto filter) {
        return getAllEnvironment(filter).stream()
                .map(EnvironmentDto::getId)
                .collect(Collectors.toList());
    }

    @Override
    public EnvironmentDto addEnvironment(EnvironmentDto environmentDto) {
        Environment environment = environmentMapper.toEntity(environmentDto);

        Environment savedEnvironment = environmentRepository.save(environment);

        return environmentMapper.toDto(savedEnvironment);
    }

    @Override
    public EnvironmentDto getEnvironmentById(Long environmentId) {
        Environment environment = environmentRepository.findById(environmentId).orElseThrow(EntityNotFoundException::new);

        return environmentMapper.toDto(environment);
    }

    @Override
    public void addReferenceToEnvironment(Long environmentId, Long referenceId) {
        Environment environment = environmentRepository.findById(environmentId).orElseThrow(EntityNotFoundException::new);

        environment.getReference().add(referenceId);
    }


}