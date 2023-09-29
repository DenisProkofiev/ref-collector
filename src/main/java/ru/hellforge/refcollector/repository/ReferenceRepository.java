package ru.hellforge.refcollector.repository;

import java.sql.SQLData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.Reference;

/**
 * ReferenceRepository.
 *
 * @author dprokofev
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long>, JpaSpecificationExecutor<Reference> {

    @Query(value = "SELECT * FROM references r WHERE r.id NOT IN (SELECT id FROM environment_reference_relation)", nativeQuery = true)
    SQLData getAllGlobalReferences();
}