package ru.hellforge.refcollector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TagDto.
 *
 * @author dprokofev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
  private Long id;
  private String tagName;
}