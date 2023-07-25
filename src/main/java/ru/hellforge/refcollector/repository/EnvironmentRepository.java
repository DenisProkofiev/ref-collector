package ru.hellforge.refcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hellforge.refcollector.model.entity.Environment;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

}
