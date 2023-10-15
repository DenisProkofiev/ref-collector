package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReferenceDto.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceImportDto {
  @JsonProperty("name")
  private String name;
  @JsonProperty("url")
  private String url;
  @JsonProperty("description")
  private String description;
}