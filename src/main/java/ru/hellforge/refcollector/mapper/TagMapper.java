package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagImportDto;
import ru.hellforge.refcollector.model.entity.Tag;
import ru.hellforge.refcollector.util.BaseOperationService;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * TagMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING)
public abstract class TagMapper {
    @Autowired
    protected BaseOperationService operationService;

   // @Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(tagDto.getObjectCode()))")
    public abstract Tag toEntity(TagDto tagDto);

//@Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(tag.getObjectCode()))")
    public abstract TagDto toDto(Tag tag);

    public abstract List<TagDto> toDtoList(List<Tag> tagList);

    public abstract List<Tag> toEntityList(List<TagDto> tagDtoList);

  //  @Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(tag.getObjectCode()))")
    public abstract TagImportDto entityToImportDto(Tag tag);

    public abstract List<TagImportDto> entityListToImportDtoList(List<Tag> tagList);

    //@Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(tagImportDto.getObjectCode()))")
    public abstract Tag importDtoToEntity(TagImportDto tagImportDto);

    public abstract List<Tag> importDtoListToEntityList(List<TagImportDto> tagImportDtoList);
}