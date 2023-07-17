package ru.hellforge.refcollector.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.ReferenceTagRelation;

/**
 * ReferenceTagRelationRepository.
 *
 * @author dprokofev
 */
@Repository
public interface ReferenceTagRelationRepository extends JpaRepository<ReferenceTagRelation, Long> {

  List<ReferenceTagRelation> findAllByTagId(Long tagId);

  List<ReferenceTagRelation> findAllByTagIdIn(List<Long> tagId);

  List<ReferenceTagRelation> findAllByReferenceIdIn(List<Long> referenceId);

}
