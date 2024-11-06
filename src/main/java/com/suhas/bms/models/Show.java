package com.suhas.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "shows")
public class Show extends BaseModel{

    private String name;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    private List<Features> featuresList;
}
