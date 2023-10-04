package ru.hellforge.refcollector.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReferenceFilter.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceFilterDto {
  private Long environmentId;
  private List<Long> tagsIdList;
  private Boolean globalReferences;
}