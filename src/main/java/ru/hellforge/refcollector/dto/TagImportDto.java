package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TagDto.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagImportDto {
  @JsonProperty("objectCode")
  private String objectCode;
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
}