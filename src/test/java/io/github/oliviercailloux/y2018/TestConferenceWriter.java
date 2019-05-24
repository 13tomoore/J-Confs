package io.github.oliviercailloux.y2018;

import static org.junit.Assert.*;

import org.junit.Test;

import io.github.oliviercailloux.y2018.jconfs.ConferenceWriter;

public class TestConferenceWriter {

	@Test
	public void testFileExists() {
		assertEquals(ConferenceWriter.icsFileExists("threeConferences"),true);
		assertEquals(ConferenceWriter.icsFileExists("Calendartest2"),true);
		assertEquals(ConferenceWriter.icsFileExists("stvrgtvrthb"),false);
	}

}
