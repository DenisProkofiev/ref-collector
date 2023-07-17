package ru.hellforge.refcollector.service;

import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagFilter;

import java.util.List;

/**
 * TagService.
 *
 * @author dprokofev
 */
public interface TagService {

    List<TagDto> getAllTag(TagFilter filter);

    TagDto saveTag(TagDto tagDto);

    TagDto getById(Long tagId);

    List<TagDto> getTagDtoListByIdList(List<Long> tagIdList);

    void deleteById(Long id);
}