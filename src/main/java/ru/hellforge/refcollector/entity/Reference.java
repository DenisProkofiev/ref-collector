package ru.hellforge.refcollector.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reference.
 *
 * @author dprokofev
 */
@Data
@Entity
@Table(name = "references")
@NoArgsConstructor
public class Reference {

  @Id
  @Column(name = "id")
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

}