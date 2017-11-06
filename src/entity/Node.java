package entity;

import tools.SortedArrayList;

//树中的节点，分为叶子节点和索引节点，索引节点包含根节点和非根索引节点
//树的度最小为3也就是说树的节点最多有两个值，超过两个值将会分裂
public class Node implements Comparable<Node>{
	private boolean leaf;
	private boolean root;
	private SortedArrayList<String> nodeData;
	private SortedArrayList<Node> children;
	private Node nextLeaf;
	private Node parent;
	
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public boolean isRoot() {
		return root;
	}
	public void setRoot(boolean root) {
		this.root = root;
	}
	public SortedArrayList<String> getNodeData() {
		return nodeData;
	}
	public void setNodeData(SortedArrayList<String> nodeData) {
		this.nodeData = nodeData;
	}
	public Node getNextLeaf() {
		return nextLeaf;
	}
	public void setNextLeaf(Node nextLeaf) {
		this.nextLeaf = nextLeaf;
	}
	public SortedArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(SortedArrayList<Node> children) {
		this.children = children;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node(){
		this.nodeData = new SortedArrayList<>();
		this.children = new SortedArrayList<>();
	}
	
	@Override
	public int compareTo(Node o) {
		return this.nodeData.get(0).compareTo(o.nodeData.get(0));
	}
	
}
