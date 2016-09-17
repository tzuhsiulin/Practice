package org.lintzuhsiu.fptree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNode {

	private int label;
	private int counter;
	private TreeNode parent;
	private List<TreeNode> child;

	public TreeNode() {
		this.child = new ArrayList<TreeNode>();
		this.label = -1;
	}
	
	public TreeNode(int label, int counter) {
		this.child = new ArrayList<TreeNode>();
		this.label = label;
		this.counter = counter;
	}

	public TreeNode(int label, TreeNode parent) {
		this.child = new ArrayList<TreeNode>();
		this.label = label;
		this.parent = parent;
		this.counter = 1;
	}

	public int getLabel() {
		return label;
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getCount() {
		return counter;
	}

	public TreeNode getChildren(int label) {
		for (Iterator<TreeNode> iter = child.iterator(); iter.hasNext();) {
			TreeNode node = iter.next();
			if (node.getLabel() == label)
				return node;
		}
		return null;
	}
	
	public List<TreeNode> getChildren() {
		return child;
	}

	public void addCount() {
		++counter;
	}
	
	public void addCount(int i) {
		counter += i;
	}

	public void addChild(TreeNode node) {
		child.add(node);
	}
	
	public void setParent(TreeNode pNode) {
		this.parent = pNode;
	}
	
	public void setLabel(int l) {
		this.label = l;
	}
	
	public void setCount(int i) {
		this.counter = i;
	}

	public void clearChild() {
		child = null;
	}

}
