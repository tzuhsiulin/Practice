package org.lintzuhsiu.apriori;
import java.util.ArrayList;
import java.util.List;

public class Itemset {

	private List<Integer> key;
	private List<Integer> set;

	public Itemset() {
		key = new ArrayList<Integer>();
		set = new ArrayList<Integer>();
	}
	
	public Itemset(List<Integer> mKey, List<Integer> mSet) {
		key = mKey;
		set = mSet;
	}

	public void addSubset(Integer data) {
		set.add(data);
	}

	public int getSubset(int index) {
		return set.get(index);
	}

	public int getSetCount() {
		return set.size();
	}

	public List<Integer> getSet() {
		return set;
	}

	public void addKeyItem(Integer data) {
		key.add(data);
	}

	public int getKeyItem(int index) {
		return key.get(index);
	}

	public int getKeyCount() {
		return key.size();
	}

	public List<Integer> getKey() {
		return key;
	}
	
	public int getHashCode() {
		return key.toString().hashCode();
	}

}
