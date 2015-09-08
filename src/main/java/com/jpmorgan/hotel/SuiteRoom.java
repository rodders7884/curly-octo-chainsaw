package com.jpmorgan.hotel;

/**
 * SuiteRoom
 */
public class SuiteRoom {

    /**
     * id
     */
    private String id;

    /**
     *
     * @param id
     */
    public SuiteRoom(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param facilities
     * @return
     */
    public double calculatePrice(String... facilities) {
        double price = 0;
        for (String facility : facilities) {
            if (facility.equals("ROOM_BREAKFAST")) {
                price = price + 5.0;
            } else if (facility.equals("ENSUITE_BATHROOM")) {
                price = price + 4.0;
            } else if (facility.equals("INTERNET")) {
                price = price + 2.0;
            } else if (facility.equals("LATE_CHECKOUT")) {
                price = price + 1.0;
            } else if (facility.equals("SWIMMINGPOOL")) {
                price = price + 1.0;
            }
        }
        return price;
    }
}
