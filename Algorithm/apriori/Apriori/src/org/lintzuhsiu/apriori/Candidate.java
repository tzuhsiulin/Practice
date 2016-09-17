package org.lintzuhsiu.apriori;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Candidate {

	public Candidate() {
	}
	
	public Map<Integer, Itemset> createCandidate(Map<Integer, Itemset> data) {
		List<Itemset> itemList = new ArrayList<Itemset>(data.values());
		final int listCount = itemList.size();
		final int initCapcity = (listCount * (listCount - 1)) / 2;
		Map<Integer, Itemset> candidate = new LinkedHashMap<Integer, Itemset>(initCapcity, 100);
		List<Integer> newKey, newSet;
		Itemset newItemset;

		try {
			for (int i = 0; i < listCount - 1; ++i) {
				for (int j = i + 1; j < listCount; ++j) {
					newKey = combinationKey(itemList.get(i), itemList.get(j));
					newSet = combinationSet(itemList.get(i), itemList.get(j));
					if (newSet != null && isFitness(newSet)) {
						newItemset = new Itemset(newKey, newSet);
						candidate.put(newItemset.getHashCode(), newItemset);
					}
				}
			}
		} finally {
			itemList.clear();
			itemList = null;			
		}		
		return candidate;
	}

	public Map<Integer, Itemset> createCandidate(int depth, Map<Integer, Itemset> data) {
		List<Itemset> itemList = new ArrayList<Itemset>(data.values());
		final int itemSize = depth - 2;
		Map<Integer, Itemset> candidate = new LinkedHashMap<Integer, Itemset>();
		List<Integer> newKey, newSet, subset;
		Itemset newItemset;
		
		try {
			subset  = new ArrayList<Integer>();
			for (int i = 0, listCount = itemList.size(); i < listCount - 1; ++i) {			
				for (int j = i + 1; j < listCount; ++j) {
					newKey = combinationKey(itemList.get(i), itemList.get(j));
					
					if (itemList.get(i).getKeyItem(0) < itemList.get(j).getKeyItem(0))
						break;
					
					if (!isSimilar(itemList.get(i), itemList.get(j), itemSize))
						continue;
					
					getSubset(depth, newKey, subset);
					if (isSuperset(subset, data)) {
						newSet = combinationSet(itemList.get(i), itemList.get(j));
						if (newSet != null && isFitness(newSet)) {
							newItemset = new Itemset(newKey, newSet);
							candidate.put(newItemset.getHashCode(), newItemset);
						}
					}
					else {
						newKey.clear();
						newKey = null;
					}
				}
			}
		} finally {
			itemList.clear();
			itemList = null;
		}		
		return candidate;
	}
	
	private boolean isFitness(List<Integer> set) {
		if (set.size() < Apriori.MIN) {
			set.clear();
			set = null;
			return false;
		}
		return true;
	}
	
	private List<Integer> combinationSet(Itemset itemset1, Itemset itemset2) {
		List<Integer> newSet = new ArrayList<Integer>();
		final int set1Count = itemset1.getSetCount();
		final int set2Count = itemset2.getSetCount();

		for (int i = 0, j = 0; i < set1Count && j < set2Count;) {
			if (newSet.size() + (set1Count - i) < Apriori.MIN || newSet.size() + (set2Count - j) < Apriori.MIN) {
				newSet.clear();
				newSet = null;
				return null;
			}
			if (itemset1.getSubset(i) == itemset2.getSubset(j)) {
				newSet.add(itemset1.getSubset(i));
				++j;
				++i;
			} else if (itemset1.getSubset(i) > itemset2.getSubset(j))
				++j;
			else
				++i;
		}
		return newSet;
	}
	
	private List<Integer> combinationKey(Itemset itemset1, Itemset itemset2) {
		List<Integer> newKey = new ArrayList<Integer>();
		newKey.addAll(itemset1.getKey());
		newKey.add(itemset2.getKeyItem(itemset1.getKeyCount() - 1));	
		return newKey;			
	}
	
	private boolean isSimilar(Itemset item1, Itemset item2, int itemSize) {
		for (int k = 0; k < itemSize; ++k)
			if (item1.getKeyItem(k) != item2.getKeyItem(k))
				return false;
		return true;
	}
	
	private boolean isSuperset(List<Integer> subset, Map<Integer, Itemset> data) {
		for (int i = 0, setSize = subset.size(); i < setSize; ++i) {
			Object obj = data.get(subset.get(i));
			if (obj == null) {
				subset.clear();
				return false;
			}
		}
		subset.clear();
		return true;
	}
	
	private void getSubset(int depth, List<Integer> key, List<Integer> subset) {
		final int count = depth - 1;
		List<Integer> item = new ArrayList<Integer>();;
		
		for (int i = 0; i < count; ++i) {
			for (int j = i + 1; j < depth; ++j) {
				item.add(key.get(i));

				for (int k = j; k < depth && item.size() < count; ++k)
					item.add(key.get(k));
				
				if (item.size() == count)
					subset.add(item.toString().hashCode());
				item.clear();
			}
		}
		item = null;
	}

}
