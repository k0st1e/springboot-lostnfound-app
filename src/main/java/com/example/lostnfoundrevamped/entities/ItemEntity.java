package com.example.lostnfoundrevamped.entities;

import com.example.lostnfoundrevamped.entities.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid", nullable = false)
    private Integer itemid;

    @Column(name = "itemname", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String itemname;

    @Column(name = "itemdescription", nullable = false, length = 64)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String itemdescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemstatus", nullable = false, length = 8)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private ItemStatus itemstatus;

    @Column(name = "itemlocation", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String itemlocation;

    @Column(name = "itemcontact", nullable = false, length = 16)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String itemcontact;

    @Lob
    @Column(name = "fileblob", columnDefinition="MEDIUMBLOB")
    private byte[] fileblob;

    @Column(name = "itemdate", nullable = false)
    private Instant itemdate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_entity_categoryid", nullable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_username", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "itemEntity", orphanRemoval = true)
    private Set<MessageEntity> messageEntities = new LinkedHashSet<>();
}