package ru.hellforge.refcollector.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportProperties {
    private String destination;
    private String fileName;
    private String file;
    private Boolean reference;
    private Boolean tag;
    private Boolean environment;
    private Boolean relation;
}
