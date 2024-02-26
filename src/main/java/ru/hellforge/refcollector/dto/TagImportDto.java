package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
  private UUID objectCode;
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
}