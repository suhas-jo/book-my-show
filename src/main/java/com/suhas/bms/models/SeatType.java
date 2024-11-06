package com.suhas.bms.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SeatType extends BaseModel{

    private String name;
}
