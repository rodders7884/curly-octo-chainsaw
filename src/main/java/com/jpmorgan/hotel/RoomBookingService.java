package com.jpmorgan.hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jpmorgan.hotel.exceptions.FacilityException;
import com.jpmorgan.hotel.exceptions.UnavailableException;
import com.jpmorgan.hotel.model.Facility;
import com.jpmorgan.hotel.model.GreatViewRoom;
import com.jpmorgan.hotel.model.HotelRoom;
import com.jpmorgan.hotel.model.StandardRoom;
import com.jpmorgan.hotel.model.SuiteRoom;

/**
 * SRBS
 */
public class RoomBookingService {
    /**
     * availableRooms
     */
    private HashMap<String, HotelRoom> availableRooms = new HashMap<>();

    /**
     *
     * @param id
     * @param facilities
     * @return
     */
    public double quoteRoom(String id, String... facilities) throws FacilityException, UnavailableException {
	HotelRoom room = availableRooms.get(id);
	if (room == null) {
	    throw new UnavailableException("Unavailable Room");
	}
	List<Facility> facilityList = validateFacilites(facilities);

	double price = room.calculatePrice(facilityList);

	// only remove when no exceptions have been thrown!
	availableRooms.remove(id);

	return price;
    }

    private List<Facility> validateFacilites(String... facilities) throws FacilityException {

	// Currently these facilities have to a near perfect match for the enum
	// we could put some more intelligence in here maybe
	List<Facility> facilityList = new ArrayList<>();
	for (String facility : facilities) {
	    if (StringUtils.isBlank(facility)) {
		// just ignore!
		continue;
	    }
	    try {
		facilityList.add(Facility.valueOf(facility.trim().toUpperCase()));
	    } catch (IllegalArgumentException e) {
		throw new FacilityException("Unrecognised Facility " + facility);
	    }
	}
	return facilityList;
    }

    /**
     *
     * @return
     */
    public Collection<HotelRoom> getAvailableRooms() {
	return availableRooms.values();
    }

    /**
     *
     * @param room
     */
    public void addGreatViewRoom(GreatViewRoom room) {
	availableRooms.put(room.getId(), room);
    }

    /**
     *
     * @param room
     */
    public void addStandardRoom(StandardRoom room) {
	availableRooms.put(room.getId(), room);
    }

    /**
     *
     * @param room
     */
    public void addSuiteRoom(SuiteRoom room) {
	availableRooms.put(room.getId(), room);
    }

}
