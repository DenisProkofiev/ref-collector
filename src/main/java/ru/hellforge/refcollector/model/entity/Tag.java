package ru.hellforge.refcollector.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Tag.
 *
 * @author dprokofev
 */
@Data
@Entity
@NoArgsConstructor
public class Tag extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}