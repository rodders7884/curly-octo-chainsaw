package com.jpmorgan.hotel.model;

import java.util.List;

import com.jpmorgan.hotel.exceptions.FacilityException;

/**
 * SuiteRoom - prices per facility and reasonable!
 */
public class SuiteRoom extends HotelRoom {

    /**
     *
     * @param id
     */
    public SuiteRoom(String id) {
	super(RoomType.SUITE, id);
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
	    switch (facility) {
	    case ROOM_BREAKFAST:
		price = price + 5.0;
		break;
	    case ENSUITE_BATHROOM:
		price = price + 4.0;
		break;
	    case INTERNET:
		price = price + 2.0;
		break;
	    case LATE_CHECKOUT:
		price = price + 1.0;
		break;
	    case SWIMMINGPOOL:
		price = price + 1.0;
		break;
	    default:
		throw new FacilityException("No price for " + facility);
	    }
	}
	return price;
    }
}
