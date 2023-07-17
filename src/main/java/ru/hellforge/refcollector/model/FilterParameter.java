package ru.hellforge.refcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FilterParametr.
 *
 * @author dprokofev
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterParameter {
    private Long id;
    private ParameterType parameterType;

    private enum ParameterType {
        TAG, CREATE_DATE
    }
}