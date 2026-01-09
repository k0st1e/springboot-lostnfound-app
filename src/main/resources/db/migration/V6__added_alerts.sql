CREATE TABLE alerts
(
    alertid              VARCHAR(255) NOT NULL,
    alertmsg             VARCHAR(64)  NOT NULL,
    alertseen            BIT(1)       NOT NULL,
    user_entity_username VARCHAR(16)  NOT NULL,
    CONSTRAINT pk_alerts PRIMARY KEY (alertid)
);

ALTER TABLE alerts
    ADD CONSTRAINT FK_ALERTS_ON_USER_ENTITY_USERNAME FOREIGN KEY (user_entity_username) REFERENCES user (username);