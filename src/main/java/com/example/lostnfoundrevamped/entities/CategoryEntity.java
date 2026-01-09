package com.example.lostnfoundrevamped.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "item_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid", nullable = false)
    private Integer categoryid;

    @Column(name = "categoryname", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String categoryname;

    @OneToMany(mappedBy = "categoryEntity", orphanRemoval = true)
    private Set<ItemEntity> itemEntities = new LinkedHashSet<>();
}