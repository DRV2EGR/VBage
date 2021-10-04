package ru.vbage.payload;

import lombok.Data;

@Data
public class SendMessagePayload {

    Long id_to;
    String body;
}
