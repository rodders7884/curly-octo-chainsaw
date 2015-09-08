package com.jpmorgan.hotel.model;

import java.util.List;

import com.jpmorgan.hotel.exceptions.FacilityException;

public abstract class HotelRoom {

    private String id;
    private RoomType type;

    public HotelRoom(RoomType type, String id) {
	super();
	this.type = type;
	this.id = id;
    }

    public abstract double calculatePrice(List<Facility> facilityList) throws FacilityException;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public RoomType getType() {
	return type;
    }

    public void setType(RoomType type) {
	this.type = type;
    }

}
