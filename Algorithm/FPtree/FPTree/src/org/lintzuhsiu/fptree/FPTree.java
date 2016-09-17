package org.lintzuhsiu.fptree;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FPTree {
	
	public static final String FILE_NAME = "T15I6N0.5KD1000K.txt";
	public static final int COMMODITY_TOTAL = 500 + 1;
	public static final int MINIMUM_SUPPORT = (int) (1000000 * 0.06);
	
	private int frequentCount;
	private int[] frequency;
	private Database db;
	private Queue<TreeNode> pattern;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		FPTree fptree = new FPTree();
		fptree.getFrequentPattern();
		
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
	public FPTree() {
		frequency = new int[COMMODITY_TOTAL];
		db = new Database(FILE_NAME);	
		db.getFrequency(frequency);
	}
	
	public void getFrequentPattern() {
		pattern = new LinkedList<TreeNode>();
		HeaderTable headerTable;
		
		try {
			headerTable = buildTree();
			FPGrowth(headerTable, new LinkedList<Integer>());
			System.out.println(frequentCount);
		} finally {
			frequency = null;
			pattern = null;
		}
	}
	
	private HeaderTable buildTree() {
		HeaderTable headerTable = new HeaderTable();
		List<Integer> transaction = new LinkedList<Integer>();
		TreeNode root, currentNode, parentNode;
		TransactionSort sort = new TransactionSort();
		int value;

		try {
			root = new TreeNode();
			while (db.readLine(transaction, frequency, MINIMUM_SUPPORT)) {
				Collections.sort(transaction, sort);
				parentNode = root;
				for (Iterator<Integer> iter = transaction.iterator(); iter.hasNext();) {
					value = iter.next();
					currentNode = parentNode.getChildren(value);
					if (currentNode == null) {
						currentNode = new TreeNode(value, parentNode);
						parentNode.addChild(currentNode);
						headerTable.addReference(value, currentNode);
					}
					else
						currentNode.addCount();
					parentNode = currentNode;
				}
				transaction.clear();
			}
		} finally {
			Arrays.fill(frequency, 0);
			sort = null;
		}
		
		return headerTable;
	}
	
	private void getFrequency(List<TreeNode> nodeList) {
		TreeNode currentNode;
		int count, listLength;
		
		listLength = nodeList.size();
		for (int i = 0; i < listLength; ++i) {
			currentNode = nodeList.get(i);
			count = currentNode.getCount();
			while (currentNode.getParent().getLabel() != -1) {
				currentNode = currentNode.getParent();
				frequency[currentNode.getLabel()] += count;
			}
		}
	}
	
	private void findPattern(TreeNode leaf) {
		TreeNode currentNode;
		
		currentNode = leaf;
		pattern.add(currentNode);
		while (currentNode.getParent().getLabel() != -1) {
			currentNode = currentNode.getParent();
			pattern.add(currentNode);
		}
	}
	
	private HeaderTable buildTree(List<TreeNode> nodeList) {
		HeaderTable headerTable = new HeaderTable();
		TreeNode root, parentNode, currentNode, tmp;
		int count;
		
		root = new TreeNode();
		for (int i = 0, listLength = nodeList.size(); i < listLength; ++i) {
			findPattern(nodeList.get(i));
			parentNode = root;
			tmp = pattern.poll();
			count = tmp.getCount();
			while (pattern.size() > 0) {
				tmp = pattern.poll();
				tmp.clearChild();
				
				if (frequency[tmp.getLabel()] < MINIMUM_SUPPORT)
					continue;
				
				currentNode = parentNode.getChildren(tmp.getLabel());
				if (currentNode == null) {
					currentNode = new TreeNode(tmp.getLabel(), parentNode);
					currentNode.setCount(count);
					parentNode.addChild(currentNode);
					headerTable.addReference(tmp.getLabel(), currentNode);
				}
				else
					currentNode.addCount(count);
				parentNode = currentNode;
			}
		}
		return headerTable;
	}
	
	private void FPGrowth(HeaderTable headerTable, List<Integer> fp) {
		HeaderTable newHeaderTable = null;
		int value;
		List<TreeNode> nodeList;
		
		try {
//			if (fp.size() > 0)
//				System.out.println(fp);
			
			if (headerTable.isSinglePath()) {
				for (int i = headerTable.getSize() - 1, fpSize = fp.size(); i >= 0; --i) {
					for (int j = i; j >= 0; --j) {
						++frequentCount;
						fp.add(headerTable.getTable(j));
					}
					fp.subList(fpSize, fp.size());
				}
				return;
			}
			
			while (headerTable.getSize() > 0) {
				++frequentCount;
				value = headerTable.pop();
				nodeList = headerTable.getItem(value);
				getFrequency(nodeList);
				newHeaderTable = buildTree(nodeList);
				Arrays.fill(frequency, 0);
				
				fp.add(value);
				FPGrowth(newHeaderTable, fp);
				fp.remove(fp.size() - 1);
				
				nodeList = null;
			}
		} finally {
			headerTable.destory();
		}
	}
	
	class TransactionSort implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (frequency[o1] < frequency[o2])
				return 1;
			else if (frequency[o1] > frequency[o2])
				return -1;
			return 0;
		}
	}
	
}
