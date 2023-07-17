package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceResponseDto;
import ru.hellforge.refcollector.dto.TagDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * ReferenceResponceMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public interface ReferenceResponseMapper {

   @Mapping(source = "tagDtoList", target = "tagList")
    ReferenceResponseDto toDto(ReferenceDto referenceDto, List<TagDto> tagDtoList);
}