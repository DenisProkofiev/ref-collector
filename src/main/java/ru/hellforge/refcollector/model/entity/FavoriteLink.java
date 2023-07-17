package ru.hellforge.refcollector.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * FavoriteLink.
 *
 * @author dprokofev
 */
@Entity
@NoArgsConstructor
@Table(name = "favorite_links")
public class FavoriteLink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_id")
    private Long referenceId;

}