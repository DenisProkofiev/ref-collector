package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.entity.Reference;

/**
 * ReferenceRepository.
 *
 * @author dprokofev
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {

}