package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagFilter;
import ru.hellforge.refcollector.dto.TagImportDto;

import java.util.List;
import java.util.UUID;

/**
 * TagService.
 *
 * @author dprokofev
 */
public interface TagService {

    List<TagDto> getAllTag(TagFilter filter);

    TagDto saveTag(TagDto tagDto);

    List<TagDto> saveTagList(List<TagDto> referenceDtoList);

    TagDto getById(Long tagId);

    List<TagDto> getTagDtoListByObjectCodeList(List<UUID> tagObjectCodeList);

    void deleteById(Long id);

    void deleteByObjectCode(UUID objectCode);

    List<TagImportDto> getAllImportTag();

    List<TagImportDto> importTag(List<TagImportDto> tagImportDtoList);
}