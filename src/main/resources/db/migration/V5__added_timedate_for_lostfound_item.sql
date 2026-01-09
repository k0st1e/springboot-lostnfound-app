ALTER TABLE item
    ADD itemdate datetime NULL;

ALTER TABLE item
    MODIFY itemdate datetime NOT NULL;