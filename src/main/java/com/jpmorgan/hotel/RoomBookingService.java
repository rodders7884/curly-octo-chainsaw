package com.jpmorgan.hotel;

import java.util.*;

import static java.util.Collections.unmodifiableSet;

/**
 * SRBS
 */
public class RoomBookingService {
    /**
     * availableRooms
     */
    private HashMap<String, Object> availableRooms = new HashMap<String, Object>();

    /**
     *
     * @param id
     * @param facilities
     * @return
     */
    public double quoteRoom(String id, String... facilities) {
        Object room = availableRooms.get(id);
        availableRooms.remove(id);

        if(room instanceof GreatViewRoom){
            return ((GreatViewRoom)room).calculatePrice(facilities);
        }
        if(room instanceof StandardRoom){
            return ((StandardRoom)room).calculatePrice(facilities);
        }
        if(room instanceof SuiteRoom){
            return ((SuiteRoom)room).calculatePrice(facilities);
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public Collection<?> getAvailableRooms() {
        return availableRooms.values();
    }

    /**
     *
     * @param room
     */
    public void addGreatViewRoom(GreatViewRoom room){
      availableRooms.put(room.getId(), room);
    }

    /**
     *
     * @param room
     */
    public void addStandardRoom(StandardRoom room){
        availableRooms.put(room.getId(), room);
    }

    /**
     *
     * @param room
     */
    public void addSuiteRoom(SuiteRoom room){
        availableRooms.put(room.getId(), room);
    }

}
