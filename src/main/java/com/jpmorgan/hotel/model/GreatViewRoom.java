package com.jpmorgan.hotel.model;

import java.util.List;

import com.jpmorgan.hotel.exceptions.FacilityException;

/**
 * GreatView Room - prices per facility and hiked up a bit!
 */
public class GreatViewRoom extends HotelRoom {

    /**
     * 
     * @param id
     */
    public GreatViewRoom(String id) {
	super(RoomType.GREATVIEW, id);
    }

    /**
     *
     * @param facilities
     * @return
     * @throws FacilityException
     */
    public double calculatePrice(List<Facility> facilityList) throws FacilityException {
	double price = 0;
	for (Facility facility : facilityList) {
	    // THere is no reason in principle why the prices for GreatView and Suite
	    // should have a simple mathematical relationship.
	    // so lets put the full price in here, not double it at the end.
	    switch (facility) {
	    case ROOM_BREAKFAST:
		price = price + 10.0;
		break;
	    case ENSUITE_BATHROOM:
		price = price + 8.0;
		break;
	    case INTERNET:
		price = price + 4.0;
		break;
	    case LATE_CHECKOUT:
		price = price + 2.0;
		break;
	    case SWIMMINGPOOL:
		price = price + 2.0;
		break;
	    default:
		throw new FacilityException("No price for " + facility);
	    }
	}
	return price;
    }
}
