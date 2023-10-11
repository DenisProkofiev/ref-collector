package ru.hellforge.refcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportProperties {
    private String destination;
    private Boolean reference;
    private Boolean target;
    private Boolean environment;
    private Boolean relation;
}
