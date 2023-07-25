package ru.hellforge.refcollector.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Reference.
 *
 * @author dprokofev
 */
@Data
@Entity
@Table(name = "references")
@NoArgsConstructor
public class Reference {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "is_favorite")
    private Boolean isFavorite;

}