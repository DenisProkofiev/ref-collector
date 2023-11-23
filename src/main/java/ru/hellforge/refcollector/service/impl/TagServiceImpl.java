package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagFilter;
import ru.hellforge.refcollector.dto.TagImportDto;
import ru.hellforge.refcollector.mapper.TagMapper;
import ru.hellforge.refcollector.model.entity.Reference;
import ru.hellforge.refcollector.model.entity.Tag;
import ru.hellforge.refcollector.repository.TagRepository;
import ru.hellforge.refcollector.service.TagService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.hellforge.refcollector.util.BaseOperation.notEqual;

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
    public List<TagDto> saveTagList(List<TagDto> referenceDtoList) {
        List<Tag> tags = tagMapper.toEntityList(referenceDtoList);

        return tagMapper.toDtoList(tagRepository.saveAll(tags));
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

    @Override
    public List<TagImportDto> getAllImportTag() {
        return tagMapper.entityListToImportDtoList(tagRepository.findAll());
    }

    @Override
    public List<TagImportDto> importTag(List<TagImportDto> tagImportDtoList) {
        List<TagImportDto> tags = compareImportTag(tagImportDtoList);
        List<Tag> savedTags = tagRepository.saveAll(tagMapper.importDtoListToEntityList(tags));

        return tagMapper.entityListToImportDtoList(savedTags);
    }

    private List<TagImportDto> compareImportTag(List<TagImportDto> tagImportDtoList) {
        List<Tag> tags = tagRepository.findAll();
        List<TagImportDto> newImportDtoList = new ArrayList<>();

        for (TagImportDto tagImportDto : newImportDtoList) {
            for (Tag tag : tags) {
                if(notEqual(tagImportDto.getName(), tag.getName()))
                    newImportDtoList.add(tagImportDto);
            }
        }

        return newImportDtoList;
    }

}