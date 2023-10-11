package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.mapper.ReferenceResponseMapper;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * AccumulatesResponseServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class AccumulatesResponseServiceImpl implements AccumulatesResponseService {
    private final EnvironmentService environmentService;
    private final ReferenceService referenceService;
    private final TagService tagService;
    private final ReferenceTagRelationService referenceTagRelationService;
    private final ReferenceResponseMapper referenceResponseMapper;
    private final EnvironmentReferenceRelationService environmentReferenceRelationService;


    @Override
    public List<Long> getReferenceIdListByEnvironmentIdList(List<Long> environmentIdList) {
        return environmentReferenceRelationService.getReferenceIdListByEnvironmentIdList(environmentIdList);
    }

    @Override
    public List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList, ExportProperties properties) {
        return null;
    }

    @Override
    public List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList) {
        return referenceService.getReferenceById(referenceIdList);
    }

    @Override
    public List<ReferenceResponseDto> getReferenceResponse(ReferenceFilterDto filter) {
        List<Long> referenceEnvironmentsRelation = environmentReferenceRelationService.getAllRelations().stream()
                .map(EnvironmentReferenceRelationDto::getReferenceId)
                .collect(toList());

        List<Long> environments = environmentService.getAllEnvironmentId();

        List<Long>  referenceIdList = environmentReferenceRelationService.getReferenceIdListByEnvironmentIdList(environments);

        List<ReferenceDto> allReferences = referenceService.getAllReference(filter);

        List<ReferenceDto> references = !isEmpty(referenceIdList) ? allReferences.stream()
                .filter(reference -> referenceIdList.contains(reference.getId()))
                .collect(toList()) : allReferences;

        List<ReferenceTagRelationDto> relations = referenceTagRelationService.getReferenceTagRelationByReferenceIdList(
                references.stream()
                        .map(ReferenceDto::getId)
                        .collect(toList()));

        List<TagDto> tags = tagService.getTagDtoListByIdList(
                relations.stream()
                        .map(ReferenceTagRelationDto::getTagId)
                        .collect(toList()));

        Map<Long, List<Long>> map = relations.stream()
                .collect(groupingBy(ReferenceTagRelationDto::getReferenceId,
                        Collectors.mapping(ReferenceTagRelationDto::getTagId, toList())));

        Map<Long, List<TagDto>> tagMap = new HashMap<>();

        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            tagMap.put(entry.getKey(), tags.stream()
                    .filter(tag -> entry.getValue().contains(tag.getId())).collect(toList()));
        }

        List<ReferenceResponseDto> referenceResponseList = new ArrayList<>();

        for (ReferenceDto reference : references) {
            referenceResponseList.add(referenceResponseMapper.toDto(reference, tagMap.get(reference.getId())));
        }

        return referenceResponseList;
    }



    private boolean getFilterPredicate(ReferenceFilterDto filter, Long id) {
        return isNull(filter) || isEmpty(filter.getTagsIdList()) || filter.getTagsIdList().contains(id);
    }



}