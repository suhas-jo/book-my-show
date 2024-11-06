package com.suhas.bms.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Booking extends BaseModel{

    @ManyToOne
    private Show show;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    private int totalAmount;
    @ManyToMany
    private List<ShowSeat> seatList;
    @OneToMany
    private List<Payment> payments;
    @ManyToOne
    private User user;
    private Date bookedAt;

}
