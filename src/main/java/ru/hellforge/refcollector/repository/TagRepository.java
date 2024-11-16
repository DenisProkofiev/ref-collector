package ru.hellforge.refcollector.repository;

import java.util.List;
import java.util.UUID;

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
  List<Tag> findAllByObjectCodeIn(List<String> tagObjectCodeList);

    void deleteByObjectCode(String objectCode);
}