package ru.hellforge.refcollector.model.entity.relation;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "environment_reference_relation")
public class EnvironmentReferenceRelation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "environment_id")
    private Long environmentId;

    @Column(name = "reference_id")
    private Long referenceId;

}