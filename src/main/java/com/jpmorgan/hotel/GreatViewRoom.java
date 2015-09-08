package com.jpmorgan.hotel;

/**
 * GVR
 */
public class GreatViewRoom{
    /**
     * id
     */
    private String id;

    /**
     *
     * @param id
     */
    public GreatViewRoom(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getId(){
        return id;
    }

    /**
     *
     * @param facilities
     * @return
     */
    public double calculatePrice(String... facilities) {
        double price = 0;
        for(String facility : facilities){
            if(facility.equals("ROOM_BREAKFAST")){
                price = price + 5.0;
            } else if (facility.equals("ENSUITE_BATHROOM")){
                price = price + 4.0;
            } else if (facility.equals("INTERNET")){
                price = price + 2.0;
            } else if (facility.equals("LATE_CHECKOUT")){
                price = price + 1.0;
            } else if (facility.equals("SWIMMINGPOOL")){
                price = price + 1.0;
            }
        }
        return 2 * price;
    }
}
