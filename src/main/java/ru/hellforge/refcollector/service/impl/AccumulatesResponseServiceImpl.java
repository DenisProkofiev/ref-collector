package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.mapper.ReferenceResponseMapper;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
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
    private final RelationService relationService;

    @Override
    public List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList, ExportProperties properties) {
        return null;
    }

    @Override
    public List<ReferenceDto> getReferenceDtoListByReferenceIdList(List<Long> referenceIdList) {
        return referenceService.getReferenceByIdList(referenceIdList);
    }

    @Override
    public List<ReferenceResponseDto> getReferenceResponse(ReferenceFilterDto filter) {
        List<ReferenceDto> references = referenceService.getAllReference(filter);

        List<ReferenceTagRelationDto> relations = referenceTagRelationService.getReferenceTagRelationByReferenceIdList(
                references.stream()
                        .map(ReferenceDto::getId)
                        .collect(toList()));

        List<RelationDto> relationList = relationService.getTagIdListByReferenceIdList(references.stream()
                .map(ReferenceDto::getId)
                .collect(toList()));
        System.out.println(relationList);

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

    @Override
    public JsonDataDto getExportDataDto(ExportProperties properties) {
        List<ReferenceImportDto> referenceExportList = TRUE.equals(properties.getEnvironment()) ? (referenceService.getAllImportReference()) : null;
        List<TagImportDto> tagExportList = TRUE.equals(properties.getTag()) ? tagService.getAllImportTag() : null;
        List<EnvironmentImportDto> environmentExportList = TRUE.equals(properties.getEnvironment()) ? environmentService.getAllImportEnvironment() : null;
        List<RelationImportDto> relationExportList = TRUE.equals(properties.getRelation()) ? relationService.getAllImportRelation() : null;

        return JsonDataDto.builder()
                .references(referenceExportList)
                .tags(tagExportList)
                .environments(environmentExportList)
                .relations(relationExportList)
                .build();
    }

    private boolean getFilterPredicate(ReferenceFilterDto filter, Long id) {
        return isNull(filter) || isEmpty(filter.getTagsIdList()) || filter.getTagsIdList().contains(id);
    }

}