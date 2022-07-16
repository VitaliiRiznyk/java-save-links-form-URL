package my;

import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class HttpConection {

	private String connection;

	public HttpConection(String connection) {
		super();
		this.connection = connection;
	}

	public HttpConection() {
		super();
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public void findAll(String connection, File file) throws MalformedURLException, IOException {
		URL url = new URL(connection);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		String write = "";
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(httpConnection.getInputStream(), "utf-8"))) {
			String reading = "";
			for (;;) {
				reading = reader.readLine();
				if (reading == null) {
					break;
				}
				write += reading + System.lineSeparator();
			}
			getHttpToFile(write, file);
		}
	}

	private void getHttpToFile(String string, File file) throws IOException {
		String[] str = string.split(System.lineSeparator());
		String current = "";
		String work = "";
		try (Writer writer = new FileWriter(file)) {
			for (int i = 0; i < str.length; i++) {
				if (str[i].contains("https")) {
					work = str[i];
					for (; work.contains("https");) {
						current = work.substring(work.indexOf("https"));
						current = current.substring(0, current.indexOf('"'));
						work = work.substring(work.indexOf(current) + current.length() - 1);
						writer.write(current + System.lineSeparator());
					}
				}
			}
		}
	}
}