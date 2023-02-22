package ru.hellforge.refcollector.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * ReferenceDto.
 *
 * @author dprokofev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDto {
  private Long id;
  private String name;
  private String url;
  private String description;
  private LocalDate createDate;
  private String tags;
}