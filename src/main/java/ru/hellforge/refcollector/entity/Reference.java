package ru.hellforge.refcollector.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private LocalDate createDate;

  @Column(name = "tag")
  private String tags;
}