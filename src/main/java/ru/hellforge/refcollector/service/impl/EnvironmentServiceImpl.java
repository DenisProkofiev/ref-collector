package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.EnvironmentDto;
import ru.hellforge.refcollector.dto.EnvironmentImportDto;
import ru.hellforge.refcollector.mapper.EnvironmentMapper;
import ru.hellforge.refcollector.model.entity.Environment;
import ru.hellforge.refcollector.repository.EnvironmentRepository;
import ru.hellforge.refcollector.service.EnvironmentService;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.hellforge.refcollector.util.BaseOperationService.notEqual;

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
    public List<Long> getAllEnvironmentId() {
        return getAllEnvironment().stream()
                .map(EnvironmentDto::getId)
                .collect(toList());
    }

    @Override
    public EnvironmentDto addEnvironment(EnvironmentDto environmentDto) {
        Environment environment = environmentMapper.toEntity(environmentDto);
        Environment savedEnvironment = environmentRepository.save(environment);

        return environmentMapper.toDto(savedEnvironment);
    }

    @Override
    public List<EnvironmentImportDto> getAllImportEnvironment() {
        return environmentMapper.entityListToImportDtoList(environmentRepository.findAll());
    }

    @Override
    public List<EnvironmentImportDto> importEnvironment(List<EnvironmentImportDto> environmentsImportList) {
        List<EnvironmentImportDto> newEnvironmentImports = compareImportReference(environmentsImportList);
        List<Environment> environments = environmentRepository.saveAll(environmentMapper.importDtoListToEntityList(newEnvironmentImports));

        return environmentMapper.entityListToImportDtoList(environments);
    }

    private List<EnvironmentImportDto> compareImportReference(List<EnvironmentImportDto> referenceImportList) {
        List<Environment> environments = environmentRepository.findAll();
        List<EnvironmentImportDto> newEnvironmentDtoList = new ArrayList<>();

        for (EnvironmentImportDto environmentImportDto : referenceImportList) {
            for (Environment environment : environments) {
                if (notEqual(environmentImportDto.getName(),environment.getName()))
                newEnvironmentDtoList.add(environmentImportDto);
            }
        }

        return newEnvironmentDtoList;
    }


}