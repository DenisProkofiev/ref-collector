package ru.hellforge.refcollector.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * FavoriteLink.
 *
 * @author dprokofev
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "environment")
public class Environment extends BaseEntity{
    @Column(name = "name")
    private String name;
}