package com.suhas.bms.dtos;

import lombok.Data;

@Data
public class ResponseDto {

    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;
}
