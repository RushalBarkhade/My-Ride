package com.example.myride.roomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class RecentPlacesEntity {
    @PrimaryKey
    private int id;
    private String name;
    private Date date;

    public RecentPlacesEntity(String name, Date date) {
        this.name = name;
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
