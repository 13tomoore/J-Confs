package io.github.oliviercailloux.y2018.jconfs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.io.text.VCardReader;
public class Test  {
	public static void main(String [] args) throws IOException {
		PathStep test =new PathStep("Paris","La Courneuve",TransportType.TRAIN);
		System.out.println("hello");
		
		ConferenceReader test2=new ConferenceReader(new File ("Jean_Dupont.vcf"));
		test2.createConference();
		
		VCard test4=new VCard();
		VCardReader reader=new VCardReader("Jean_Dupont.vcf");
		System.out.println("ici2");
		System.out.println(reader);
		while((test4=reader.readNext())!=null){
			System.out.println("ici");
			System.out.println(reader);
		}
	
		
	}
}