package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.entity.Tag;

/**
 * TagRepository.
 *
 * @author dprokofev
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
