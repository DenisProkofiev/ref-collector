package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.Reference;

import java.sql.SQLData;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ReferenceRepository.
 *
 * @author dprokofev
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findAllByIdIn(List<Long> idList);

    void deleteByObjectCode(String uuid);

    Optional<Reference> findByObjectCode(String uuid);
}