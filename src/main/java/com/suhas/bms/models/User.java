package com.suhas.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User extends BaseModel{

    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookingList;
}
