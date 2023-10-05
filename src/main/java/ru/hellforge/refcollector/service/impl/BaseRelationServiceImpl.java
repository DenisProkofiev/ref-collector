package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.BaseRelationDto;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.mapper.BaseReferenceMapper;
import ru.hellforge.refcollector.model.entity.BaseRelation;
import ru.hellforge.refcollector.repository.BaseRelationRepository;
import ru.hellforge.refcollector.service.BaseRelationService;

import java.util.List;

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
    public List<BaseRelationDto> getListRelationFromReferenceDto(ReferenceDto referenceDto) {
        return null;
    }


}
