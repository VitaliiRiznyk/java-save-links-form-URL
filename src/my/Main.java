package my;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

	public static void main(String[] args) {
		String connection = "https://rabota.ua/ua";
		//String connection = "https://www.facebook.com/"; // to try something different
		HttpConection hc = new HttpConection();
		File file = new File("links.txt");
		
		try {
			hc.findAll(connection,file);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}