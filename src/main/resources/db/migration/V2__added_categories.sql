CREATE TABLE item_category
(
    categoryid   INT AUTO_INCREMENT NOT NULL,
    categoryname VARCHAR(32)        NOT NULL,
    CONSTRAINT pk_item_category PRIMARY KEY (categoryid)
);