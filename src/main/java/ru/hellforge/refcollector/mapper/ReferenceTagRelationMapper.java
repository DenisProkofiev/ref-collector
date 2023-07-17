package ru.hellforge.refcollector.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.ReferenceTagRelationDto;
import ru.hellforge.refcollector.model.entity.ReferenceTagRelation;

/**
 * ReferenceTagRelationMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public interface ReferenceTagRelationMapper {

  ReferenceTagRelationDto toDto(ReferenceTagRelation relation);

  ReferenceTagRelation toEntity(ReferenceTagRelationDto relationDto);

  List<ReferenceTagRelationDto> toDtoList(List<ReferenceTagRelation> referenceTagRelationList);
}