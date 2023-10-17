package ru.hellforge.refcollector.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagImportDto;
import ru.hellforge.refcollector.model.entity.Tag;

/**
 * TagMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public interface TagMapper {
    Tag toEntity(TagDto tagDto);
    TagDto toDto(Tag tag);
    List<TagDto> toDtoList(List<Tag> tagList);
    List<Tag> toEntityList(List<TagDto> tagDtoList);
    List<TagImportDto> entityListToImportDtoList(List<Tag> tagList);
    List<Tag> importDtoListToEntityList(List<TagImportDto> tagImportDtoList);
}