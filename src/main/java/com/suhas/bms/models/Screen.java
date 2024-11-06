package com.suhas.bms.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Screen extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seatList;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Features> featuresList;
}
