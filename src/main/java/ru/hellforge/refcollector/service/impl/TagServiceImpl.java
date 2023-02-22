package ru.hellforge.refcollector.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.entity.Tag;
import ru.hellforge.refcollector.mapper.TagMapper;
import ru.hellforge.refcollector.repository.TagRepository;
import ru.hellforge.refcollector.service.TagService;

/**
 * TagServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @Override
  public List<TagDto> getAllTag() {
    return tagMapper.toDtoList(tagRepository.findAll());
  }

  @Override
  public TagDto saveTag(TagDto tagDto) {
    Tag tag = tagMapper.toEntity(tagDto);
    Tag savedTag = tagRepository.save(tag);

    return tagMapper.toDto(savedTag);
  }

}