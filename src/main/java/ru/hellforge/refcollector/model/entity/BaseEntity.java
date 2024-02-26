package ru.hellforge.refcollector.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "org.hibernate.type.UUIDBinaryType")
    @Column(name = "UUID", updatable = false, unique = true)
    private UUID objectCode;

    @PrePersist
    private void prePersist() {
        setObjectCode(UUID.randomUUID());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(UUID uuid) {
        this.objectCode = uuid;
    }
}
