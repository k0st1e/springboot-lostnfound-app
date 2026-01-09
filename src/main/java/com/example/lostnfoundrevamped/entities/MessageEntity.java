package com.example.lostnfoundrevamped.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid", nullable = false)
    private Integer messageid;

    @Column(name = "messagecontent", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String messagecontent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_entity_itemid", nullable = false)
    private ItemEntity itemEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_username", nullable = false)
    private UserEntity userEntity;
}