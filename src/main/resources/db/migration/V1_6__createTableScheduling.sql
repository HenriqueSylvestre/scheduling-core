CREATE TABLE scheduling (
    uuid CHAR(36) NOT NULL,
    send DATETIME NOT NULL,
    message VARCHAR(255) NOT NULL,
    customer_uuid CHAR(36) NOT NULL,
    scheduling_email_uuid CHAR(36) DEFAULT NULL,
    scheduling_sms_uuid CHAR(36) DEFAULT NULL,
    scheduling_push_uuid CHAR(36) DEFAULT NULL,
    scheduling_whatsapp_uuid CHAR(36) DEFAULT NULL,
    PRIMARY KEY (uuid),
    CONSTRAINT FK_SCHEDULING_CUSTOMER FOREIGN KEY (customer_uuid) REFERENCES customer(uuid),
    CONSTRAINT FK_SCHEDULING_SCHEDULING_EMAIL FOREIGN KEY (scheduling_email_uuid) REFERENCES scheduling_email(uuid),
    CONSTRAINT FK_SCHEDULING_SCHEDULING_SMS FOREIGN KEY (scheduling_sms_uuid) REFERENCES scheduling_sms(uuid),
    CONSTRAINT FK_SCHEDULING_SCHEDULING_PUSH FOREIGN KEY (scheduling_push_uuid) REFERENCES scheduling_push(uuid),
    CONSTRAINT FK_SCHEDULING_SCHEDULING_WHATSAPP FOREIGN KEY (scheduling_whatsapp_uuid) REFERENCES scheduling_whatsapp(uuid)
)