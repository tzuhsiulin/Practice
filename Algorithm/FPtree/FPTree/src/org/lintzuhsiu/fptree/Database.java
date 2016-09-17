package org.lintzuhsiu.fptree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Database {

	private String fileName;
	private BufferedReader bfr;
	
	public Database(String fileName) {
		this.fileName = fileName;		
	}
	
	public boolean readLine(List<Integer> data, int[] frequency, int minSupport) {
		String line;
		int value = 0;
		char c;

		try {			
			line = bfr.readLine();
			if (line == null) {
				bfr.close();
				bfr = null;
				return false;
			}
			
			for (int i = 0, lineLength = line.length(); i < lineLength; ++i) {
				c = line.charAt(i);
				if (c != ' ' && c != ',')
					value = value == 0 ? (c - '0') : (value * 10) + (c - '0');
				else if (c == ',') {
					if (frequency[value] >= minSupport)
						data.add(value);
					value = 0;
				}
			}

			if (frequency[value] >= minSupport)
				data.add(value);
			value = 0;
		} catch(IOException e) {
			e.printStackTrace();			
		}
		return true;
	}

	public void getFrequency(int[] transaction) {
		String line;
		int value = 0;
		char c;

		try {
			bfr = new BufferedReader(new FileReader(fileName));
			while ((line = bfr.readLine()) != null) {
				for (int i = 0, lineLength = line.length(); i < lineLength; ++i) {
					c = line.charAt(i);
					if (c != ' ' && c != ',')
						value = value == 0 ? (c - '0') : (value * 10) + (c - '0');
					else if (c == ',') {
						++transaction[value];
						value = 0;
					}
				}
				++transaction[value];
				value = 0;
			}
			bfr.close();
			bfr = new BufferedReader(new FileReader(fileName));
		} catch(IOException e) {
			e.printStackTrace();			
		}
	}
	
}
