CREATE TABLE scheduling_email (
    uuid CHAR(36),
    sended DATETIME DEFAULT NULL,
    status_id INT(11) NOT NULL,
    PRIMARY KEY (uuid),
    CONSTRAINT FK_SCHEDULING_EMAIL_STATUS FOREIGN KEY (status_id) REFERENCES status(id)
);