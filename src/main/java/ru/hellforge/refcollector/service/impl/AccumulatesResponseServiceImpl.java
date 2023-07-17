package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.mapper.ReferenceResponseMapper;
import ru.hellforge.refcollector.service.AccumulatesResponseService;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;
import ru.hellforge.refcollector.service.TagService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * AccumulatesResponseServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class AccumulatesResponseServiceImpl implements AccumulatesResponseService {

    private final ReferenceService referenceService;
    private final TagService tagService;
    private final ReferenceTagRelationService referenceTagRelationService;
    private final ReferenceResponseMapper referenceResponseMapper;

    @Override
    public List<ReferenceResponseDto> getReferenceResponse(ReferenceFilterDto filter) {


        List<ReferenceDto> references = referenceService.getAllReference(filter);

        List<ReferenceTagRelationDto> relations = referenceTagRelationService.getReferenceTagRelationByReferenceIdList(
                references.stream()
                        .map(ReferenceDto::getId)
                        .toList());

        List<TagDto> tags = tagService.getTagDtoListByIdList(
                relations.stream()
                        .map(ReferenceTagRelationDto::getTagId)
                        .toList());

        Map<Long, List<Long>> map = relations.stream()
                .collect(groupingBy(ReferenceTagRelationDto::getReferenceId,
                        Collectors.mapping(ReferenceTagRelationDto::getTagId, Collectors.toList())));

        Map<Long, List<TagDto>> tagMap = new HashMap<>();

        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            tagMap.put(entry.getKey(), tags.stream()
                    .filter(tag -> entry.getValue().contains(tag.getId())).toList());
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