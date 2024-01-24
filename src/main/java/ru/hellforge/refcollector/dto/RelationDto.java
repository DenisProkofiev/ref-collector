package ru.hellforge.refcollector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationDto {
    private Long id;
    private String objectCode;
    private String referenceObjectCode;
    private String tagObjectCode;
    private String environmentObjectCode;
    private Long referenceId;
    private Long tagId;
    private Long environmentId;
    private String type;

}