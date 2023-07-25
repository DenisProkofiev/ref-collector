package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagFilter;
import ru.hellforge.refcollector.mapper.TagMapper;
import ru.hellforge.refcollector.model.entity.Tag;
import ru.hellforge.refcollector.repository.TagRepository;
import ru.hellforge.refcollector.service.TagService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
    public List<TagDto> getAllTag(TagFilter filter) {
        return tagMapper.toDtoList(tagRepository.findAll());
    }

    @Override
    public TagDto saveTag(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        Tag savedTag = tagRepository.save(tag);

        return tagMapper.toDto(savedTag);
    }

    @Override
    public TagDto getById(Long tagId) {
        Tag tagFromDB = tagRepository.findById(tagId).orElseThrow(EntityNotFoundException::new);

        return tagMapper.toDto(tagFromDB);
    }

    @Override
    public List<TagDto> getTagDtoListByIdList(List<Long> tagIdList) {
        List<Tag> tags = tagRepository.findAllByIdIn(tagIdList);

        return tagMapper.toDtoList(tags);
    }

    @Override
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

}