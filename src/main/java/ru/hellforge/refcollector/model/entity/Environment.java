package ru.hellforge.refcollector.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * FavoriteLink.
 *
 * @author dprokofev
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "environment")
public class Environment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}