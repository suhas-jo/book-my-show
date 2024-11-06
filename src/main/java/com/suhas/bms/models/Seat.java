package com.suhas.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{

    private String seatNum;
    @ManyToOne
    private SeatType seatType;
    private int rowNum;
    private int colNum;
}
