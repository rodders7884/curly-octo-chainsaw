package com.jpmorgan.hotel.model;

import java.util.List;

import com.jpmorgan.hotel.exceptions.FacilityException;

/**
 * StandardRoom - price based on simple facility count
 */
public class StandardRoom extends HotelRoom {

    /**
     *
     * @param id
     */
    public StandardRoom(String id) {
	super(RoomType.STANDARD, id);
    }

    /**
     *
     * @param facilities
     * @return
     * @throws FacilityException
     */
    public double calculatePrice(List<Facility> facilityList) throws FacilityException {
	for (Facility facility : facilityList) {
	    switch (facility) {
	    case ROOM_BREAKFAST:
	    case ENSUITE_BATHROOM:
	    case INTERNET:
	    case LATE_CHECKOUT:
	    case SWIMMINGPOOL:
		break;
	    default:
		throw new FacilityException("Facility " + facility + " not available for standard room");
	    }
	}

	if (facilityList.size() > 3) {
	    return 3 * 3 + (facilityList.size() - 3) * 6;
	} else {
	    return facilityList.size() * 3;
	}
    }

}
