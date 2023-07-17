package ru.hellforge.refcollector.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.mapper.ReferenceMapper;
import ru.hellforge.refcollector.model.entity.Reference;
import ru.hellforge.refcollector.repository.ReferenceRepository;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;

import static java.util.Objects.isNull;

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
  private final ReferenceTagRelationService referenceTagRelationService;

  @Override
  public List<ReferenceDto> getAllReference(ReferenceFilterDto filter) {
    List<Reference> referenceDtoList = (isNull(filter) || isNull(filter.getTagsIdList())) ?
        referenceRepository.findAll() :
        referenceRepository.findAllById(referenceTagRelationService.getReferenceIdListByTagId(filter.getTagsIdList().get(0)));

    return referenceMapper.toDtoList(referenceDtoList);
  }

  @Override
  public ReferenceDto getReferenceById(Long referenceId) {
    Reference referenceFromBD = referenceRepository.findById(referenceId).orElseThrow(EntityNotFoundException::new);

    return referenceMapper.toDto(referenceFromBD);
  }

  @Override
  @Transactional
  public ReferenceDto saveReference(ReferenceDto referenceDto) {
    Reference reference = referenceMapper.toEntity(referenceDto);

    referenceTagRelationService.addRelationFromReference(referenceDto);

    Reference savedReference = referenceRepository.save(reference);

    return referenceMapper.toDto(savedReference);
  }

  @Override
  public void deleteById(Long id) {
    referenceRepository.deleteById(id);
  }

}