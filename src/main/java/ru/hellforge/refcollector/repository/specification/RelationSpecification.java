package ru.hellforge.refcollector.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hellforge.refcollector.dto.ReferenceFilterDto;
import ru.hellforge.refcollector.model.entity.Relation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RelationSpecification {

    public static Specification<Relation> getSpecification(ReferenceFilterDto filter) {
        return new Specification<Relation>() {
            @Override
            public Predicate toPredicate(Root<Relation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return root.get("tagId").in(filter.getTagsIdList());
            }
        };
    }
}