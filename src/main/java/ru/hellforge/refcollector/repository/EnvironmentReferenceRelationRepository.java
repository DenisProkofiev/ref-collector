package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hellforge.refcollector.model.entity.relation.EnvironmentReferenceRelation;

import java.util.Collection;
import java.util.List;

public interface EnvironmentReferenceRelationRepository extends JpaRepository<EnvironmentReferenceRelation, Long> {
    List<EnvironmentReferenceRelation> findAllByEnvironmentId(Long environmentId);

    List<Long> findAllByEnvironmentIdIn(List<Long> environmentIdList);
}
