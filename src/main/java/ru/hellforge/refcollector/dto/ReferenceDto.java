package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

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
public class ReferenceDto {
  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("url")
  private String url;
  @JsonProperty("description")
  private String description;
  @JsonProperty("createDate")
  private LocalDate createDate;
  @JsonProperty("tags")
  private List<Long> tagIdList;
  @JsonProperty("environmentId")
  private Long environmentId;
}