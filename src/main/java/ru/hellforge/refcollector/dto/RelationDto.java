package ru.hellforge.refcollector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationDto {
    private Long id;
    private UUID objectCode;
    private UUID referenceObjectCode;
    private UUID tagObjectCode;
    private UUID environmentObjectCode;
    private Long referenceId;
    private Long tagId;
    private Long environmentId;
    private String type;

}