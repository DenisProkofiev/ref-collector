package ru.hellforge.refcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hellforge.refcollector.dto.TagDto;

import java.util.List;

/**
 * FilterReference.
 *
 * @author dprokofev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceFilter {
    private List<Long> tagsIdList;
    private List<TagDto> tags;

}
