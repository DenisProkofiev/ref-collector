package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceTagRelationDto;
import ru.hellforge.refcollector.mapper.ReferenceTagRelationMapper;
import ru.hellforge.refcollector.model.entity.relation.ReferenceTagRelation;
import ru.hellforge.refcollector.repository.ReferenceTagRelationRepository;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ReferenceTagRelationServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class ReferenceTagRelationServiceImpl implements ReferenceTagRelationService {

    private final ReferenceTagRelationRepository referenceTagRelationRepository;
    private final ReferenceTagRelationMapper referenceTagRelationMapper;

    @Override
    public ReferenceTagRelationDto addRelation(ReferenceTagRelationDto relationDto) {
        ReferenceTagRelation savedRelation = referenceTagRelationRepository.save(referenceTagRelationMapper.toEntity(relationDto));

        return referenceTagRelationMapper.toDto(savedRelation);
    }

    @Override
    public void addRelationFromReference(ReferenceDto referenceDto) {
        List<ReferenceTagRelation> referenceTagRelationList = getReferenceTagListFromReference(referenceDto.getId(), referenceDto.getTagIdList());
        referenceTagRelationRepository.saveAll(referenceTagRelationList);
    }

    @Override
    public List<Long> getReferenceIdListByTagId(Long tagId) {
        return referenceTagRelationRepository.findAllByTagId(tagId).stream()
                .map(ReferenceTagRelation::getReferenceId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReferenceTagRelationDto> getReferenceTagRelationByReferenceIdList(List<Long> referenceIdList) {
        List<ReferenceTagRelation> referenceTagRelationList = referenceTagRelationRepository.findAllByReferenceIdIn(referenceIdList);
        return referenceTagRelationMapper.toDtoList(referenceTagRelationList);
    }

    @Override
    public List<ReferenceTagRelationDto> getAllReferenceTagRelation() {
        return referenceTagRelationMapper.toDtoList(referenceTagRelationRepository.findAll());
    }

    @Override
    public void deleteRelation(Long relationId) {
        referenceTagRelationRepository.deleteById(relationId);
    }

    private List<ReferenceTagRelation> getReferenceTagListFromReference(Long referenceId, List<Long> tagIdList) {
        return tagIdList.stream()
                .map(id -> referenceTagRelationFromIds(referenceId, id))
                .collect(Collectors.toList());
    }

    private ReferenceTagRelation referenceTagRelationFromIds(Long referenceId, Long tagId) {
        return ReferenceTagRelation.builder()
                .referenceId(referenceId)
                .tagId(tagId)
                .build();
    }

}