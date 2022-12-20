package ru.hellforge.refcollector.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.GregorianCalendar;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tag.
 *
 * @author dprokofev
 */
@Data
@Entity
@NoArgsConstructor
public class Tag {
  @Id
  @Column(name = "reference_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tag_name")
  private String tagName;

  @ManyToMany(mappedBy = "tag")
  private Set<Reference> referenceList;

}