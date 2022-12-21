package ru.hellforge.refcollector.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reference.
 *
 * @author dprokofev
 */
@Data
@Entity
@NoArgsConstructor
public class Reference {
  @Id
  @Column(name = "reference_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "url")
  private String url;

  @Column(name = "description")
  private String description;

  @Column(name = "create_date")
  private LocalDateTime createDate;

  @ManyToMany
  @JoinTable(name = "reference_tag",
      joinColumns = {@JoinColumn(name = "reference_id")},
      inverseJoinColumns = {@JoinColumn(name = "tag_id")})
  private Set<Tag> tagList;
}