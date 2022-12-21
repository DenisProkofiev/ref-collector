package ru.hellforge.refcollector.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hellforge.refcollector.entity.Reference;

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