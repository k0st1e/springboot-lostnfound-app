CREATE TABLE messages
(
    messageid            INT AUTO_INCREMENT NOT NULL,
    messagecontent       VARCHAR(255)       NOT NULL,
    item_entity_itemid   INT                NOT NULL,
    user_entity_username VARCHAR(16)        NOT NULL,
    CONSTRAINT pk_messages PRIMARY KEY (messageid)
);

ALTER TABLE item
    ADD user_entity_username VARCHAR(16) NULL;

ALTER TABLE item
    MODIFY user_entity_username VARCHAR(16) NOT NULL;

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_USER_ENTITY_USERNAME FOREIGN KEY (user_entity_username) REFERENCES user (username);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_ITEM_ENTITY_ITEMID FOREIGN KEY (item_entity_itemid) REFERENCES item (itemid);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_USER_ENTITY_USERNAME FOREIGN KEY (user_entity_username) REFERENCES user (username);