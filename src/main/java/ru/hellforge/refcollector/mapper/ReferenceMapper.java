package ru.hellforge.refcollector.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.entity.Reference;

/**
 * ReferenceMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public interface ReferenceMapper {

  ReferenceDto toDto(Reference reference);

  Reference toEntity(ReferenceDto referenceDto);

  List<ReferenceDto> toDtoList(List<Reference> referenceList);

}
