package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.*;
import ru.hellforge.refcollector.mapper.ReferenceResponseMapper;
import ru.hellforge.refcollector.model.ExportProperties;
import ru.hellforge.refcollector.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;
import static ru.hellforge.refcollector.util.BaseOperationService.isObjectEmpty;

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
        List<ReferenceDto> references = null;
        List<RelationDto> relations = null;

        if (!isObjectEmpty(filter)) {
            relations = relationService.getFiltredReferenceIdList(filter);

            references = referenceService.getReferenceByIdList(relations.stream()
                    .map(RelationDto::getReferenceId)
                    .collect(toList()));

        } else {
            references = referenceService.getAllReference(filter);

            relations = relationService.getRelationListFromReferenceObjectCodeList(
                    references.stream()
                            .map(ReferenceDto::getObjectCode)
                            .collect(toList())
            );
        }

        List<TagDto> tags = tagService.getTagDtoListByObjectCodeList(
                relations.stream()
                        .filter(relation -> REF_TAG.name().equals(relation.getType()))
                        .map(RelationDto::getTagObjectCode)
                        .distinct()
                        .collect(toList()));

        Map<UUID, List<UUID>> map = relations.stream()
                .collect(groupingBy(RelationDto::getReferenceObjectCode,
                        Collectors.mapping(RelationDto::getTagObjectCode, toList())));

        Map<UUID, List<TagDto>> tagMap = new HashMap<>();

        for (Map.Entry<UUID, List<UUID>> entry : map.entrySet()) {
            tagMap.put(entry.getKey(), tags.stream()
                    .filter(tag -> entry.getValue().contains(tag.getObjectCode())).collect(toList()));
        }

        return references.stream()
                .map(reference -> referenceResponseMapper.toDto(reference, tagMap.get(reference.getObjectCode())))
                .collect(toList());
    }

    public List<Long> getFilterResponse(ReferenceFilterDto filter) {
        return relationService.getFiltredReferenceIdList(filter).stream()
                .map(RelationDto::getId)
                .collect(toList());
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

}