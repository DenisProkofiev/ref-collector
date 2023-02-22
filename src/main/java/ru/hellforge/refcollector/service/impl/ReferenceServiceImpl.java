package ru.hellforge.refcollector.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilter;
import ru.hellforge.refcollector.entity.Reference;
import ru.hellforge.refcollector.mapper.ReferenceMapper;
import ru.hellforge.refcollector.repository.ReferenceRepository;
import ru.hellforge.refcollector.service.ReferenceService;

/**
 * ReferenceServiceImpl.
 *
 * @author dprokofev
 */
@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {

  private final ReferenceRepository referenceRepository;
  private final ReferenceMapper referenceMapper;

  @Override
  public List<ReferenceDto> getAllReference(ReferenceFilter filter) {
    List<Reference> referenceList = Objects.isNull(filter.getTags()) ?
        referenceRepository.findAll() : referenceRepository.findByTagsIn(getAllTags(filter.getTags()));

    return referenceMapper.toDtoList(referenceList);
  }

  @Override
  public List<ReferenceDto> getReferenceByTag(String tag) {
    List<Reference> filteredReference = referenceRepository.findByTagsIn(getAllTags(tag));

    return referenceMapper.toDtoList(filteredReference);
  }

  @Override
  @Transactional
  public ReferenceDto saveReference(ReferenceDto referenceDto) {
    Reference reference = referenceMapper.toEntity(referenceDto);

    reference.setCreateDate(LocalDate.now());

    Reference savedReference = referenceRepository.save(reference);

    return referenceMapper.toDto(savedReference);
  }

  private List<String> getAllTags(String tags) {
    return List.of(tags.trim().split(" "));
  }

}