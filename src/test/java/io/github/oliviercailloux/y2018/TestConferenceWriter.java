package io.github.oliviercailloux.y2018;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import org.junit.Test;

import io.github.oliviercailloux.y2018.jconfs.Conference;
import io.github.oliviercailloux.y2018.jconfs.ConferenceWriter;
import io.github.oliviercailloux.y2018.jconfs.InvalidConferenceFormatException;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.validate.ValidationException;

public class TestConferenceWriter {

	@Test
	public void testFileExists() {
		assertEquals(ConferenceWriter.icsFileExists("threeConferences"),true);
		assertEquals(ConferenceWriter.icsFileExists("Calendartest2"),true);
		assertEquals(ConferenceWriter.icsFileExists("stvrgtvrthb"),false);
	}

	@Test
	public void TestAddConf() throws ParseException, ValidationException, IOException, ParserException, URISyntaxException {
		URL u =new URL("http://example.com/");
		Conference c = new Conference(u);
		c.setCity("Paris");
		c.setCountry("France");
		c.setEndDate("09/06/2019");
		c.setFeeRegistration(1.06);
		c.setStartDate("06/06/2019");
		c.setTitle("IBM quantum computers");

		ConferenceWriter.addConference("threeConferences", c);

	}
	
	@Test
	public void TestDeleteConf() throws ParseException, ValidationException, IOException, ParserException, URISyntaxException, InvalidConferenceFormatException {
		URL u =new URL("http://example.com/");
		Conference c = new Conference(u);
		c.setCity("Paris");
		c.setCountry("France");
		c.setEndDate("09/06/2019");
		c.setFeeRegistration(1.06);
		c.setStartDate("06/06/2019");
		c.setTitle("IBM quantum computers");

		ConferenceWriter.deleteConference("threeConferences", c);
		
	}

}
