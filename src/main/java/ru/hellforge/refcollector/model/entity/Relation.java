package ru.hellforge.refcollector.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "relation")
public class Relation extends BaseEntity{
    @Column(name = "reference_object_code")
    private String referenceObjectCode;

    @Column(name = "tag_object_code")
    private String tagObjectCode;

    @Column(name = "environment_object_code")
    private String environmentObjectCode;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "environment_id")
    private Long environmentId;

    @Column(name = "type")
    private String type;
}