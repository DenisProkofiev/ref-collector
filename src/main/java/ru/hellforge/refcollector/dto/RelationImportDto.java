package ru.hellforge.refcollector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationImportDto {
    private String objectCode;
    private Long referenceId;
    private Long tagId;
    private Long environmentId;
    private String type;
}

