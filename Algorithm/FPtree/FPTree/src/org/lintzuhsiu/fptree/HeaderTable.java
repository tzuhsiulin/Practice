package org.lintzuhsiu.fptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HeaderTable {
	
	private Map<Integer, List<TreeNode>> table;
	private LinkedList<Integer> order;
	private boolean singlePath;
	
	public HeaderTable() {
		table = new HashMap<Integer, List<TreeNode>>();
		singlePath = true;
		order = new LinkedList<Integer>();
	}
	
	public void addReference(int label, TreeNode node) {
		List<TreeNode> nodeList = table.get(label);
		if (nodeList == null) {
			nodeList = new ArrayList<TreeNode>();
			table.put(label, nodeList);
			order.add(label);
		}
		singlePath = false;
		nodeList.add(node);
	}
	
	public int getSize() {
		return table.size();
	}
	
	public List<TreeNode> getItem(int value) {
		List<TreeNode> result = table.get(value);
		table.remove(value);
		return result;
	}
	
	public int getTable(int i) {
		return order.get(i);
	}
	
	public boolean isSinglePath() {
		return singlePath;
	}
	
	public Integer pop() {
		return order.pop();
	}
	
	public void destory() {		
		table = null;
		order = null;
	}
	
}
