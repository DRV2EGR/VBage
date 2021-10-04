package ru.vbage.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message extends BaseEntity{
    protected String msg_body;

    @ManyToOne
    protected User sender;

    @ManyToOne
    protected User reciever;
}
