package ru.hellforge.refcollector.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.entity.Reference;

/**
 * ReferenceRepository.
 *
 * @author dprokofev
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long>, JpaSpecificationExecutor<Reference> {

  List<Reference> findByTagsIn(List<String> tagList);

}