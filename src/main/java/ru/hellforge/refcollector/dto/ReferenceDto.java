package ru.hellforge.refcollector.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private LocalDateTime createDate;
}