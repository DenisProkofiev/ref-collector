package ru.hellforge.refcollector.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.Tag;

/**
 * TagRepository.
 *
 * @author dprokofev
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
  List<Tag> findAllByIdIn(List<Long> tagIdList);

}