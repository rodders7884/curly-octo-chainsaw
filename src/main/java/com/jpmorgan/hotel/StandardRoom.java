package com.jpmorgan.hotel;

/**
 * STandardRoovm
 */
public class StandardRoom{
    /**
     * id
     */
    private String id;

    /**
     *
     * @param id
     */
    public StandardRoom(String id) {
        this.id = id;
    }

    /**
     *
     * @param facilities
     * @return
     */
    public double calculatePrice(String... facilities) {
        if(facilities.length > 3){
            return 3 * 3 + (facilities.length - 3) * 6;
        } else {
            return facilities.length * 3;
        }
    }

    /**
     *
     * @return
     */
    public String getId(){
        return id;
    }
}
