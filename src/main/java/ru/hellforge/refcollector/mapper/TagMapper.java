package ru.hellforge.refcollector.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.entity.Tag;

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

}