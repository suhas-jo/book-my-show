package com.suhas.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShowSeatType extends BaseModel{

    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private int price;
}
