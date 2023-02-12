package ru.hellforge.refcollector.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.ReferenceDto;
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
  public List<ReferenceDto> getAllReference() {
    return referenceMapper.toDtoList(referenceRepository.findAll());
  }

  @Override
  @Transactional
  public ReferenceDto saveReference(ReferenceDto referenceDto) {
    Reference reference = referenceMapper.toEntity(referenceDto);
    reference.setCreateDate(LocalDateTime.now());

    Reference savedReference = referenceRepository.save(reference);

    return referenceMapper.toDto(savedReference);
  }

}