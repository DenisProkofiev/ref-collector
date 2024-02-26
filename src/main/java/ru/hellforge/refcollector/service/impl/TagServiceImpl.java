package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.TagDto;
import ru.hellforge.refcollector.dto.TagFilter;
import ru.hellforge.refcollector.dto.TagImportDto;
import ru.hellforge.refcollector.mapper.TagMapper;
import ru.hellforge.refcollector.model.entity.Tag;
import ru.hellforge.refcollector.repository.TagRepository;
import ru.hellforge.refcollector.service.RelationService;
import ru.hellforge.refcollector.service.TagService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static ru.hellforge.refcollector.enums.EntityType.TAG;
import static ru.hellforge.refcollector.enums.RelationType.REF_TAG;
import static ru.hellforge.refcollector.util.BaseOperationService.convertStringToUUID;
import static ru.hellforge.refcollector.util.BaseOperationService.notEqual;
import static ru.hellforge.refcollector.util.Constants.OBJECT_CODE_NOT_CREATED;

/**
 * TagServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final RelationService relationService;
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final Sort tagSorting = Sort.by(ASC, ("name"));

    @Override
    public List<TagDto> getAllTag(TagFilter filter) {
        return tagMapper.toDtoList(tagRepository.findAll(tagSorting));
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
    public List<TagDto> getTagDtoListByObjectCodeList(List<UUID> tagObjectCodeList) {
        List<Tag> tags = tagRepository.findAll(tagSorting).stream()
                .filter(new Predicate<Tag>() {
                    @Override
                    public boolean test(Tag tag) {
                        String objectCodeTag = OBJECT_CODE_NOT_CREATED;

                        if (nonNull(tag.getObjectCode()))
                            objectCodeTag = tag.getObjectCode().toString();

                        return tagObjectCodeList.contains(objectCodeTag);
                    }
                })
                .collect(toList());

        return tagMapper.toDtoList(tags);
    }

    @Override
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByObjectCode(UUID objectCode) {
        tagRepository.deleteByObjectCode(objectCode.toString());
        relationService.deleteByTypeAndObjectCode(REF_TAG, TAG, objectCode);
    }

    @Override
    public List<TagImportDto> getAllImportTag() {
        return tagMapper.entityListToImportDtoList(tagRepository.findAll(tagSorting));
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
                if (notEqual(tagImportDto.getName(), tag.getName()))
                    newImportDtoList.add(tagImportDto);
            }
        }

        return newImportDtoList;
    }

}