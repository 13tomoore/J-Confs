package io.github.oliviercailloux.y2018.jconfs;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.ValidationException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

/**
 * This class allows to read and iCalelndar file and creates a conference object
 * from a parsed iCalendar file
 * 
 *
 */
public class ConferenceReader {

	/**
	 * Parse an iCalendar object with ical4j API function took from source :
	 * https://www.programcreek.com/java-api-examples/?api=net.fortuna.ical4j.model.property.Method
	 * 
	 * @param filePath
	 * @throws IOException
	 * @throws ParserException
	 */

	// Reader reader = new InputStreamReader(inputStream); on va mettre ca en place
	// apres
	public static void ReadCalendarFile(Reader read) throws IOException, ParserException, NumberFormatException {
		CalendarBuilder builder = new CalendarBuilder();
		System.out.println(3);
		Calendar calendar = builder.build(read);
		System.out.println(4);

		// Iterating over the calendar component

		for (Component component : calendar.getComponents()) {
			System.out.println("Component [" + component.getName() + "]");
			// Iterating over the component property
			for (Property property : component.getProperties()) {
				System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
			}
		}
	}

	/**
	 * Creates conference from ics file, function inspired by function
	 * readCalendarFile
	 * 
	 * @param filepath
	 *            not <code> null</code>
	 * @return Conference
	 * @throws IOException
	 * @throws ParserException
	 * @throws ParseException
	 * @throws ValidationException
	 */

	public static Conference createConference(Reader read)
			throws IOException, ParserException, ParseException, NumberFormatException {
		Conference conf = null;

		CalendarBuilder builder = new CalendarBuilder();
		Calendar calendar = builder.build(read);
		Component confCompo = calendar.getComponent("X-CONFERENCE");

		// the url is the primary key of a conference
		URL confURL = new URL(confCompo.getProperty("URL").getValue());
		conf = new Conference(confURL);

		// add the others attributes
		conf.setTitle(confCompo.getProperty("SUMMARY").getValue());
		conf.setCountry(confCompo.getProperty("X-COUNTRY").getValue());
		
		
		
		//convert DateTime pattern to LocalDate pattern

		String stringDTSTART=confCompo.getProperty("X-DTSTART").getValue();
		String stringDTEND=confCompo.getProperty("X-DTSTART").getValue();

		
		
		DateTimeFormatter formatBefore=DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter formatAfter=DateTimeFormatter.ofPattern("dd/MM/yyy");
		
		LocalDate dtStart=LocalDate.parse(stringDTSTART,formatBefore);
		LocalDate dtEnd=LocalDate.parse(stringDTEND,formatBefore);
		
		conf.setStartDate(dtStart.format(formatAfter));
		conf.setEndDate(dtEnd.format(formatAfter));
		
		conf.setFeeRegistration(Double.parseDouble(confCompo.getProperty("X-FEE").getValue()));
		conf.setCity(confCompo.getProperty("X-CITY").getValue());
	
		return conf;

	}
}
