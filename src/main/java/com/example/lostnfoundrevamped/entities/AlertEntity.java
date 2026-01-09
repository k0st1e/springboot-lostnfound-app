package com.example.lostnfoundrevamped.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

// New alert -> If alert is !seen make it RED, WHEN READ -> YELLOW.
// With that weird ternary thing NAV BAR WILL BE RED OR YELLOW(th:class or th:attr)?????!
// Need a button to flip that state I guess.
// So if alerts are present, red -> go inside hit some button to flip it, and then it's yellow.
@Getter
@Setter
@Entity
@Table(name = "alerts")
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alertid")
    private Integer alertId;

    @Column(name = "alertmsg", nullable = false, length = 64)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String alertMessage;

    @Column(name = "alertseen", nullable = false)
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean isSeen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_username", nullable = false)
    private UserEntity userEntity;
}