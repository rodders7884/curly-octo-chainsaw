package com.jpmorgan.hotel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestRoomPricing {
    // Write the test class first so you can prove the refactor does not change
    // behaviour!

    private static RoomBookingService rbs = new RoomBookingService();

    private static final String ROOM_BREAKFAST = "ROOM_BREAKFAST";
    private static final String ENSUITE_BATHROOM = "ENSUITE_BATHROOM";
    private static final String INTERNET = "INTERNET";
    private static final String LATE_CHECKOUT = "LATE_CHECKOUT";
    private static final String SWIMMINGPOOL = "SWIMMINGPOOL";

    @Before
    public void buildHotel() {
	rbs.addStandardRoom(new StandardRoom("StandardRoom1"));
	rbs.addStandardRoom(new StandardRoom("StandardRoom2"));
	rbs.addStandardRoom(new StandardRoom("StandardRoom3"));
	rbs.addStandardRoom(new StandardRoom("StandardRoom4"));
	rbs.addStandardRoom(new StandardRoom("StandardRoom5"));
	rbs.addSuiteRoom(new SuiteRoom("SuiteRoom1"));
	rbs.addSuiteRoom(new SuiteRoom("SuiteRoom2"));
	rbs.addSuiteRoom(new SuiteRoom("SuiteRoom3"));
	rbs.addSuiteRoom(new SuiteRoom("SuiteRoom4"));
	rbs.addSuiteRoom(new SuiteRoom("SuiteRoom5"));
	rbs.addGreatViewRoom(new GreatViewRoom("GreatViewRoom1"));
	rbs.addGreatViewRoom(new GreatViewRoom("GreatViewRoom2"));
	rbs.addGreatViewRoom(new GreatViewRoom("GreatViewRoom3"));
	rbs.addGreatViewRoom(new GreatViewRoom("GreatViewRoom4"));
	rbs.addGreatViewRoom(new GreatViewRoom("GreatViewRoom5"));
    }

    @Test
    public void testStandardPriceNoFacilities() {

	double price = rbs.quoteRoom("StandardRoom1");
	assertEquals(0d, price, 0.005);

    }

    @Test
    public void testStandardPriceFacilities() {
	double price = rbs.quoteRoom("StandardRoom1", ROOM_BREAKFAST);
	assertEquals(3d, price, 0.005);
	price = rbs.quoteRoom("StandardRoom2", ROOM_BREAKFAST, ENSUITE_BATHROOM);
	assertEquals(6d, price, 0.005);
	price = rbs.quoteRoom("StandardRoom3", ROOM_BREAKFAST, ENSUITE_BATHROOM, INTERNET);
	assertEquals(9d, price, 0.005);
	price = rbs.quoteRoom("StandardRoom4", ROOM_BREAKFAST, ENSUITE_BATHROOM, INTERNET, LATE_CHECKOUT);
	assertEquals(15d, price, 0.005);
	price = rbs.quoteRoom("StandardRoom5", ROOM_BREAKFAST, ENSUITE_BATHROOM, INTERNET, LATE_CHECKOUT, SWIMMINGPOOL);
	assertEquals(21d, price, 0.005);
    }

    @Test
    public void testStandardPriceFacilities2() {
	// Just to prove its not feature specific
	double price = rbs.quoteRoom("StandardRoom1", SWIMMINGPOOL);
	assertEquals(3d, price, 0.005);
	price = rbs.quoteRoom("StandardRoom4", SWIMMINGPOOL, ENSUITE_BATHROOM, INTERNET, LATE_CHECKOUT);
	assertEquals(15d, price, 0.005);
    }

    @Test
    public void testSuitePriceNoFacilities() {

	double price = rbs.quoteRoom("SuiteRoom1");
	assertEquals(0d, price, 0.005);
    }

    @Test
    public void testSuitePriceFacilitiesIndividual() {
	double price = rbs.quoteRoom("SuiteRoom1", ROOM_BREAKFAST);
	assertEquals(5d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom2", ENSUITE_BATHROOM);
	assertEquals(4d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom3", INTERNET);
	assertEquals(2d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom4", LATE_CHECKOUT);
	assertEquals(1d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom5", SWIMMINGPOOL);
	assertEquals(1d, price, 0.005);
    }

    @Test
    public void testSuitePriceFacilitiesCombos() {
	double price = rbs.quoteRoom("SuiteRoom1", ROOM_BREAKFAST, LATE_CHECKOUT);
	assertEquals(6d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom2", ENSUITE_BATHROOM, ROOM_BREAKFAST);
	assertEquals(9d, price, 0.005);
	price = rbs.quoteRoom("SuiteRoom3", INTERNET, SWIMMINGPOOL);
	assertEquals(3d, price, 0.005);

    }

    @Test
    public void testGreatViewPriceFacilitiesIndividual() {
	double price = rbs.quoteRoom("GreatViewRoom1", ROOM_BREAKFAST);
	assertEquals(10d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom2", ENSUITE_BATHROOM);
	assertEquals(8d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom3", INTERNET);
	assertEquals(4d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom4", LATE_CHECKOUT);
	assertEquals(2d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom5", SWIMMINGPOOL);
	assertEquals(2d, price, 0.005);
    }

    @Test
    public void testGreatViewPriceFacilitiesCombos() {
	double price = rbs.quoteRoom("GreatViewRoom1", ROOM_BREAKFAST, LATE_CHECKOUT);
	assertEquals(12d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom2", ENSUITE_BATHROOM, ROOM_BREAKFAST);
	assertEquals(18d, price, 0.005);
	price = rbs.quoteRoom("GreatViewRoom3", INTERNET, SWIMMINGPOOL);
	assertEquals(6d, price, 0.005);

    }

    @Test
    public void testSomeFailureCases() {
	fail("TODO");
    }

}
