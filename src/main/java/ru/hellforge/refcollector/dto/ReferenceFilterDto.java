package ru.hellforge.refcollector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ReferenceFilter.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceFilterDto extends Filter {
    private Long environmentId;
    private List<Long> tagsIdList;
    private Boolean globalReferences;
}