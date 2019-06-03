package io.github.oliviercailloux.y2018.jconfs;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



/**
 * @author huong,camille
 */

public class Conference {
	private URL url;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double registrationFee;
	private String country;
	private String city;
	
		
	/**	 
	 *  This is a constructor which initializes the conference object
	 * @param url
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param registrationFee
	 * @param country
	 * @param city
	 */
	public Conference(URL url,String title,String startDate,String endDate,Double registrationFee, String country,String city){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.url=url;
		this.title=title;
		this.startDate= LocalDate.parse(startDate, formatter);
		this.endDate= LocalDate.parse(endDate, formatter);
		this.registrationFee=registrationFee;
		this.country=country;
		this.city=city;
	}
	
	/**
	 * This is a getter which return the URL  	
	 * @return url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * This is a getter which return the title  	
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This is a getter which return the date start  	
	 * @return startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * This is a getter which return the date end 	
	 * @return endDate
	 */
	public  LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * This is a getter which return the fee of registration	
	 * @return registrationFee
	 */
	public Double getFeeRegistration() {
		return registrationFee;
	}
	
	/**
	 * This is a getter which return the country	
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * This is a getter which return the city	
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * This is a constructor which initializes the conference object 
	 * @param url primary key
	 */
	public Conference(URL url) {
		this.url =Objects.requireNonNull(url);
		this.title =new String("");
		this.startDate = LocalDate.now();
		this.endDate = LocalDate.now();
		this.registrationFee = 0.0;
		this.country = "";
		this.city="";
	}
	
	@Override
	/**
	 * Compare the conference to object by comparing all attributes 
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Conference) {
			Conference conference2 = (Conference) obj;
			System.out.println(toString());
			System.out.println(conference2.toString());
			if (title.equals(conference2.title) && url.equals(conference2.url)
					&& startDate.equals(conference2.startDate) && endDate.equals(conference2.endDate)
					&& registrationFee == conference2.registrationFee && city.equals(conference2.city)
					&& country.equals(conference2.country)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode(){
		return Objects.hash(url,title,registrationFee,startDate,endDate,country,city);
	}


	@Override
	public String toString() {
		return "Conference [url=" + url + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", registrationFee=" + registrationFee + ", country=" + country+ "city = " + city +"]";
	}
	
	
	

}
