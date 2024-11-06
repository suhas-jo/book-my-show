package com.suhas.bms.dtos;

import lombok.Data;

import java.util.List;
@Data
public class RequestDto {

    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
