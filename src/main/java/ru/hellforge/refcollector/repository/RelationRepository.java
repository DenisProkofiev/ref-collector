package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.Relation;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {
    List<Long> findAllByTagIdAndType(Long tagId, String type);
    List<Relation> findAllByReferenceIdIn(List<Long> referenceIdList);
    void deleteAllByReferenceId(Long referenceId);
    void deleteAllByEnvironmentId(Long environmentId);
}
