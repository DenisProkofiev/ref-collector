package ru.hellforge.refcollector.service;

import java.util.List;
import ru.hellforge.refcollector.dto.TagDto;

/**
 * TagService.
 *
 * @author dprokofev
 */
public interface TagService {

  List<TagDto> getAllTag();

  TagDto saveTag(TagDto tagDto);
}