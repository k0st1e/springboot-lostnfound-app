CREATE TABLE item
(
    itemid                     INT AUTO_INCREMENT NOT NULL,
    itemname                   VARCHAR(32)        NOT NULL,
    itemdescription            VARCHAR(64)        NOT NULL,
    itemstatus                 VARCHAR(8)         NOT NULL,
    itemlocation               VARCHAR(32)        NOT NULL,
    itemcontact                VARCHAR(16)        NOT NULL,
    fileblob                   MEDIUMBLOB         NULL,
    category_entity_categoryid INT                NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (itemid)
);

ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_CATEGORY_ENTITY_CATEGORYID FOREIGN KEY (category_entity_categoryid) REFERENCES item_category (categoryid);