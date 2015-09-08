package com.jpmorgan.hotel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.hotel.exceptions.FacilityException;
import com.jpmorgan.hotel.exceptions.UnavailableException;
import com.jpmorgan.hotel.model.GreatViewRoom;
import com.jpmorgan.hotel.model.StandardRoom;
import com.jpmorgan.hotel.model.SuiteRoom;

public class TestRoomPricingEnhanced {
    // THese tests show the enhanced behaviour, error handling etc

    private static RoomBookingService rbs = new RoomBookingService();

    // I've deliberately continued to use Strings to represent facilities on the main quote method
    // SInce I've assumed they come from a web front end or something
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
    public void testStandardPriceMixedCaseFacility() throws FacilityException, UnavailableException {
	double price = rbs.quoteRoom("StandardRoom1", "SwImMiNgPoOl");
	assertEquals(3d, price, 0.005);
    }

    @Test
    public void testStandardPriceWithSpaces() throws FacilityException, UnavailableException {
	double price = rbs.quoteRoom("StandardRoom1", "                SwImMiNgPoOl                        ");
	assertEquals(3d, price, 0.005);
    }

    @Test
    public void testStandardPriceWithNullsAndEmpties() throws FacilityException, UnavailableException {
	double price = rbs.quoteRoom("StandardRoom1", null, "                SwImMiNgPoOl                        ", "");
	assertEquals(3d, price, 0.005);
    }

    @Test
    public void testBadFacilityStandard() throws UnavailableException {
	assertEquals(15, rbs.getAvailableRooms().size());

	try {
	    rbs.quoteRoom("StandardRoom1", "Late_Lunch");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("Unrecognised Facility Late_Lunch", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());

    }

    @Test
    public void testBadSuite() throws UnavailableException {
	try {
	    rbs.quoteRoom("SuiteRoom1", "Late_Lunch");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("Unrecognised Facility Late_Lunch", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testBadFacilityGreatView() throws UnavailableException {
	try {
	    rbs.quoteRoom("GreatViewRoom1", "Late_Lunch");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("Unrecognised Facility Late_Lunch", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testNewFacilityStandard() throws UnavailableException {
	// This test shows what happens when the program is enhanced to add a new facility
	// I'm assuming standard should not include THIS new facility!

	try {
	    rbs.quoteRoom("StandardRoom4", "CHAMPAGNE_BREAKFAST");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("Facility CHAMPAGNE_BREAKFAST not available for standard room", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testNewFacilitySuite() throws UnavailableException {
	// This test shows what happens when the program is enhanced to add a new facility but the
	// pricing code has not been updated.
	try {
	    rbs.quoteRoom("SuiteRoom4", "CHAMPAGNE_BREAKFAST");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("No price for CHAMPAGNE_BREAKFAST", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testNewFacilityGreatView() throws UnavailableException {
	// This test shows what happens when the program is enhanced to add a new facility but the
	// pricing code has not been updated.
	try {
	    rbs.quoteRoom("GreatViewRoom1", "CHAMPAGNE_BREAKFAST");
	    fail("Could have used an exception here");
	} catch (FacilityException fe) {
	    assertEquals("No price for CHAMPAGNE_BREAKFAST", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testBadRoom() throws FacilityException, UnavailableException {
	try {
	    rbs.quoteRoom("WeDontHaveOne", ENSUITE_BATHROOM);
	    fail("Could have used an exception here");
	} catch (UnavailableException fe) {
	    assertEquals("Unavailable Room", fe.getMessage());
	}
	assertEquals("Room should not have been removed!", 15, rbs.getAvailableRooms().size());
    }

    @Test
    public void testConflictingRoomIds() {
	// what happens when you call (eg) addStandardRoom with
	// an ID that has been used before for another room of the same or different type
	fail("TODO");
    }

    @Test
    public void testImSureTheresSomeMoreFailureCases() {
	// My standard technique If I remember something that needs testing but don't want to stop
	// and write it now (you can even do this before you have written the interface class you
	// need to make the test compile!)
	fail("TODO");
    }

}
