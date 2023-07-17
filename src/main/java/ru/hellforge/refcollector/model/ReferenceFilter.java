package ru.hellforge.refcollector.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hellforge.refcollector.dto.TagDto;

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

  private List<TagDto> tags;

}
