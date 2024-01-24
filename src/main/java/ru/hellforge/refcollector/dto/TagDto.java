package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class TagDto {
  @JsonProperty("objectCode")
  private String objectCode;
  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
}