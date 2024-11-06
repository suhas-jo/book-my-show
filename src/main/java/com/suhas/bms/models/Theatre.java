package com.suhas.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theatre extends BaseModel {

    private String name;
    @OneToMany
    private List<Screen> screenList;
    @ManyToOne
    private Region region;

}
