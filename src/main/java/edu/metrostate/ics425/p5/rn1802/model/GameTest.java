package edu.metrostate.ics425.p5.rn1802.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {
	Game g = new Game();
	
	@Test
	void testGame() {
		//testing if the first player is Dark
		assertEquals(g.getCurrentPlayer(), "D");
		
		//space at the top left is empty
		assertTrue(g.isEmptySpot(0));
		
		//spaces in the center are populated
		assertFalse(g.isEmptySpot(27));
		assertFalse(g.isEmptySpot(28));
		assertFalse(g.isEmptySpot(35));
		assertFalse(g.isEmptySpot(36));
		
		//look for valid spaces
		g.setValid();
		
		//checking if top left is valid which it is not
		assertFalse(g.getValid()[0]);
		
		//checking the spaces near light marks
		assertTrue(g.getValid()[19]);
		assertTrue(g.getValid()[26]);
		assertTrue(g.getValid()[37]);
		assertTrue(g.getValid()[44]);
		
		//checking space before placing
		assertEquals(g.getGrid()[27], "L");
		assertNull(g.getGrid()[19]);
		
		//placing mark and testing if spaces changed to Dark marks
		g.placeMark(19);
		assertEquals(g.getGrid()[27], "D"); //changed to Dark from light
		assertEquals(g.getGrid()[19], "D"); //changed to Dark from null
		
		//check if turn changed
		assertEquals(g.getCurrentPlayer(), "L");
		
		//check if count works
		assertEquals(g.getDarkCount(), 4);
		assertEquals(g.getLightCount(), 1);
		
		//test quit/restart
		g.restart();
		
		//check if player is dark
		assertEquals(g.getCurrentPlayer(), "D");
		
		//check if the four center pieces are correct
		assertEquals(g.getGrid()[27], "L");
		assertEquals(g.getGrid()[28], "D");
		assertEquals(g.getGrid()[35], "D");
		assertEquals(g.getGrid()[36], "L");
		
		//should only be 2 of each color
		assertEquals(g.getDarkCount(), 2);
		assertEquals(g.getLightCount(), 2);
	}

}
