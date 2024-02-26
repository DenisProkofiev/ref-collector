package ru.hellforge.refcollector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.model.entity.Reference;
import ru.hellforge.refcollector.util.BaseOperationService;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * ReferenceMapper.
 *
 * @author dprokofev
 */
@Mapper(componentModel = SPRING, imports = {UUID.class})
public abstract class ReferenceMapper {
    @Autowired
    protected BaseOperationService operationService;

   // @Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(referenceDto.getObjectCode()))")
    public abstract Reference fullDtoToEntity(ReferenceDto referenceDto);

   // @Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(reference.getObjectCode()))")
    public abstract ReferenceDto entityToFullDto(Reference reference);

    public abstract List<Reference> fullDtoListToEntityList(List<ReferenceDto> referenceDtoList);

    public abstract List<ReferenceDto> referenceListToFullDtoList(List<Reference> referenceList);

    //@Mapping(target = "objectCode", expression = "java(operationService.convertStringToUUID(referenceImportDto.getObjectCode()))")
    public abstract Reference importDtoToEntity(ReferenceImportDto referenceImportDto);

    public abstract List<Reference> importDtoListToEntityList(List<ReferenceImportDto> referenceDtoList);

   // @Mapping(target = "objectCode", expression = "java(operationService.convertUUIDToString(reference.getObjectCode()))")
    public abstract ReferenceImportDto entityToImportDto(Reference reference);

    public abstract List<ReferenceImportDto> entityListToImportDtoList(List<Reference> referenceList);

}