package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.enums.EntityType;
import ru.hellforge.refcollector.enums.RelationType;
import ru.hellforge.refcollector.model.entity.Relation;

import java.util.List;
import java.util.UUID;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long>, JpaSpecificationExecutor<Relation> {
    List<Relation> findAllByTagIdAndType(Long tagId, String type);

    List<Relation> findAllByReferenceIdIn(List<Long> referenceIdList);

    List<Relation> findAllByReferenceObjectCodeIn(List<String> referenceObjectCodeList);

    void deleteAllByReferenceId(Long referenceId);


    void deleteAllByEnvironmentId(Long environmentId);

    void deleteAllByTypeAndReferenceObjectCode(String type, String objectCode);
    void deleteAllByTypeAndTagObjectCode(String type, String objectCode);
}
