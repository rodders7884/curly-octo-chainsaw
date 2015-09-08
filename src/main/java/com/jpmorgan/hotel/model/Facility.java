package com.jpmorgan.hotel.model;

public enum Facility {

    // The advantage of using an enum here is that the compiler checks it all for you - no
    // misspellings.
    // The disadvantage is that you cannot enhance the service to add new facilities
    // without a code change. Given that the prices are so individual this is probably a god idea.

    ROOM_BREAKFAST,
    ENSUITE_BATHROOM,
    INTERNET,
    LATE_CHECKOUT,
    SWIMMINGPOOL,
    CHAMPAGNE_BREAKFAST
}
