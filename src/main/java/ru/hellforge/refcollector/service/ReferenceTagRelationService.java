package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceTagRelationDto;

import java.util.List;

/**
 * ReferenceTagRelationService.
 *
 * @author dprokofev
 */
public interface ReferenceTagRelationService {

    void deleteRelation(Long relationId);

    ReferenceTagRelationDto addRelation(ReferenceTagRelationDto relationDto);

    void addRelationFromReference(ReferenceDto referenceDto);

    List<Long> getReferenceIdListByTagId(Long tagId);

    List<ReferenceTagRelationDto> getReferenceTagRelationByReferenceIdList(List<Long> referenceIdList);

    List<ReferenceTagRelationDto> getAllReferenceTagRelation();

}
