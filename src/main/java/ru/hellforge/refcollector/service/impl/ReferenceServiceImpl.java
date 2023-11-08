package ru.hellforge.refcollector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hellforge.refcollector.dto.ReferenceDto;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.dto.ReferenceImportDto;
import ru.hellforge.refcollector.mapper.ReferenceMapper;
import ru.hellforge.refcollector.model.entity.Reference;
import ru.hellforge.refcollector.repository.ReferenceRepository;
import ru.hellforge.refcollector.service.RelationService;
import ru.hellforge.refcollector.service.ReferenceService;
import ru.hellforge.refcollector.service.ReferenceTagRelationService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static ru.hellforge.refcollector.util.BaseOperation.notEqual;

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
    private final RelationService baseRelationService;

    @Override
    public List<ReferenceDto> getAllReference(ReferenceFilterDto filter) {
        List<Reference> referenceDtoList = (isNull(filter) || isNull(filter.getTagsIdList())) ?
                referenceRepository.findAll() :
                referenceRepository.findAllById(
                        //referenceTagRelationService.getReferenceIdListByTagId(filter.getTagsIdList().get(0));
                        baseRelationService.getReferenceIdListByTagId(filter.getTagsIdList().get(0))
                );

        return referenceMapper.referenceListToFullDtoList(referenceDtoList);
    }

    @Override
    public ReferenceDto getReferenceByIdList(Long referenceId) {
        Reference referenceFromBD = referenceRepository.findById(referenceId).orElseThrow(EntityNotFoundException::new);

        return referenceMapper.entityToFullDto(referenceFromBD);
    }

    @Override
    public List<ReferenceDto> getReferenceByIdList(List<Long> referenceIdList) {
        List<Reference> references = referenceRepository.findAllByIdIn(referenceIdList);

        return referenceMapper.referenceListToFullDtoList(references);
    }

    @Override
    @Transactional
    public ReferenceDto saveReference(ReferenceDto referenceDto) {
        Reference reference = referenceMapper.fullDtoToEntity(referenceDto);
        Reference savedReference = referenceRepository.save(reference);

        return referenceMapper.entityToFullDto(savedReference);
    }

    @Override
    public List<ReferenceImportDto> getAllImportReference() {
        return referenceMapper.entityListToImportDtoList(referenceRepository.findAll());
    }

    @Override
    public List<ReferenceDto> saveReferenceList(List<ReferenceDto> referenceDtoList) {
        List<Reference> references = referenceMapper.fullDtoListToEntityList(referenceDtoList);

        return referenceMapper.referenceListToFullDtoList(referenceRepository.saveAll(references));
    }

    @Override
    public List<ReferenceImportDto> importReference(List<ReferenceImportDto> referenceImportDtoList) {
        List<ReferenceImportDto> referenceImportToSave = compareImportReference(referenceImportDtoList);
        List<Reference> savedList = referenceRepository.saveAll(referenceMapper.importDtoListToEntityList(referenceImportToSave));

        return referenceMapper.entityListToImportDtoList(savedList);
    }

    @Override
    public void deleteById(Long id) {
        referenceRepository.deleteById(id);
    }

    private List<ReferenceImportDto> compareImportReference(List<ReferenceImportDto> referenceImportList) {
        List<Reference> references = referenceRepository.findAll();
        List<ReferenceImportDto> newReferences = new ArrayList<>();

        for (ReferenceImportDto referenceImportDto : referenceImportList) {
            for (Reference reference : references) {
                if(notEqual(referenceImportDto.getUrl(),reference.getUrl())) {
                    newReferences.add(referenceImportDto);
                }
            }
        }

        return newReferences;
    }

}