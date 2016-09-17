package org.lintzuhsiu.apriori;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Apriori {

	public final static String FILE_NAME = "T15I6N0.5KD100K.txt";
	public final static int TRANSATION_TOTAL = 1000 * 100;
	public final static float MIN_SUPPORT = 0.01f;
	public final static int COMMODITY_TOTAL = 500;
	public final static int MIN = (int) (TRANSATION_TOTAL * MIN_SUPPORT);
	
	private Map<Integer, Itemset> data;
	private Candidate candidate;

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		new Apriori();
		System.out.println("°õ¦æ®É¶¡: " + (System.currentTimeMillis() - time) + "²@¬í");
	}

	public Apriori() {
		candidate = new Candidate();
		getFirstFrequentSet();
		
		for (int i = 2; data.size() != 0; ++i) {
//			System.out.println(data.size());
//			System.out.println(i);
//			Iterator iter = data.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry pair = (Map.Entry) iter.next();
//				Itemset item = (Itemset) pair.getValue();
//				System.out.println(item.getKey() + "," + item.getSetCount());
//			}
			genApriori(i);
		}
	}
	
	private void getFirstFrequentSet() {
		data = new HashMap<Integer, Itemset>(Apriori.COMMODITY_TOTAL, 100);
		readFile();
		prune(MIN);
		data = new TreeMap<Integer, Itemset>(data);
	}

	private void readFile() {
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new FileReader(Apriori.FILE_NAME));
			String line;
			int value = 0;
			char c;

			for (int i = 0; (line = bfr.readLine()) != null; ++i) {
				for (int j = 0, lineLength = line.length(); j < lineLength; ++j) {
					c = line.charAt(j);
					if (c != ' ' && c != ',')
						value = value == 0 ? (c - '0') : (value * 10) + (c - '0');
					else if (c == ',') {
						addItemset(value, i);
						value = 0;
					}
				}
				addItemset(value, i);
				value = 0;
			}
			bfr.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			bfr = null;	
		}
	}
	
	@SuppressWarnings("unchecked")
	private void prune(int min) {
		Itemset item;

		for (Iterator<?> iter = data.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<Integer, Itemset> pair = (Map.Entry<Integer, Itemset>) iter.next();
			item = (Itemset) pair.getValue();
			if (item.getSetCount() < min)
				iter.remove();
		}
	}
	
	private void addItemset(int key, int subset) {
		Itemset item = data.get(key);
		
		if (item == null) {
			item = new Itemset();
			item.addKeyItem(key);
			data.put(key, item);
		}		
		item.addSubset(subset);
	}

	private void genApriori(int depth) {
		Map<Integer, Itemset> c = depth == 2 ? candidate.createCandidate(data) : candidate.createCandidate(depth, data);
		data.clear();
		data = c;
	}

}
