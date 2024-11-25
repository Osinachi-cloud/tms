package com.maliroso.tms.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Basic(optional = false)
    @Column(name = "id", unique = true)
    protected UUID id;

    @Version
    @Column(name = "version")
    private long version;

    @CreationTimestamp
    @Column(name = "date_created")
    protected LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    protected LocalDateTime lastUpdated;

}
