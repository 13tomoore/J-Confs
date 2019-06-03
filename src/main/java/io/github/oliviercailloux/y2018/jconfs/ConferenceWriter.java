package io.github.oliviercailloux.y2018.jconfs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import com.google.common.base.Preconditions;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.XComponent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.validate.ValidationException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Property;
public class ConferenceWriter {

	public static boolean icsFileExists(String calFile) {
		Preconditions.checkNotNull(calFile);
		URL urlcalendar = ConferenceWriter.class.getResource(calFile+".ics");
		return (urlcalendar!=null);
	}

	private static Calendar openCalendar(String calFile) throws IOException, ParserException {
		Preconditions.checkNotNull(calFile);
		URL urlcalendar = ConferenceWriter.class.getResource(calFile+".ics");
		FileReader reader=new FileReader(new File(urlcalendar.getFile()));
		CalendarBuilder builder = new CalendarBuilder();
		Calendar calendar = builder.build(reader);
		return calendar;
	}

	private static Calendar createCalendar() {
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		return calendar;
	}
	
	public static void deleteConference(String calFile, Conference conference) throws IOException, ParserException, URISyntaxException, InvalidConferenceFormatException {
		
		Calendar calendar = new Calendar();
		Calendar newCalendar;
		
		Conference temp;
		if(icsFileExists(calFile)) {
			calendar=openCalendar(calFile);
		}
		else {
			calendar=createCalendar();
		}
		
		ComponentList<CalendarComponent> conflist=calendar.getComponents("X-CONFERENCE");
		for (int i=0;i<conflist.size();i++) {
			temp=ConferenceReader.createConference(conflist.get(i));
			if(temp.equals(conference)) {
				conflist.remove(i);
			}
		}
		
		newCalendar=new Calendar(conflist);
		URL resourceUrl = ConferenceWriter.class.getResource(calFile+".ics");
		File file = new File(resourceUrl.toURI());
		try(FileOutputStream fout = new FileOutputStream(file)){
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.setValidating(false);
			outputter.output(newCalendar, fout);
			fout.close();	 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the conference in form calendar 
	 * @param conference
	 * @throws ParseException
	 * @throws IOException
	 * @throws ParserException
	 * @throws ValidationException
	 * @throws URISyntaxException
	 */

	public static void addConference(String calFile, Conference conference) throws ParseException, IOException, ParserException, ValidationException, URISyntaxException{

		Calendar calendar = new Calendar();

		if(icsFileExists(calFile)) {
			calendar=openCalendar(calFile);
		}
		else {
			calendar=createCalendar();
		}

		//Creating an event
		PropertyList<Property> propertyList = new PropertyList<Property>();
		propertyList.add(new XProperty("X-DTSTART",conference.getStartDate().toString()));
		propertyList.add(new XProperty("X-DTEND",conference.getEndDate().toString()));

		propertyList.add(new Summary(conference.getTitle()));
		propertyList.add(new XProperty("X-COUNTRY",conference.getCountry().toString()));
		propertyList.add(new XProperty("X-CITY",conference.getCity().toString()));
		propertyList.add(new Url(conference.getUrl().toURI()));
		propertyList.add(new XProperty("X-FEE",conference.getFeeRegistration().toString()));
		
		XComponent meeting= new XComponent("X-CONFERENCE", propertyList);
		//add event to the calendar
		calendar.getComponents().add(meeting);

		//Saving an iCalendar file
		URL resourceUrl = ConferenceWriter.class.getResource(calFile+".ics");
		System.out.println(resourceUrl);
		File file = new File(resourceUrl.toURI());
		try(FileOutputStream fout = new FileOutputStream(file)){
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.setValidating(false);
			outputter.output(calendar, fout);
			fout.close();	 
		}catch (IOException e) {
			e.printStackTrace();
		}


	}
}

