package com.suhas.bms.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Movie extends BaseModel{
    private String name;
    private int duration;
    private double rating;
}
