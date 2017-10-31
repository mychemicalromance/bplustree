package entity;

import tools.SortedArrayList;

public class BPlusTree {
	//树的度
	private int degree;
	private Node root;
	private Node headLeaf;
	
	
	public BPlusTree(int degree){
		this.degree = degree;
	}
	
	public void add(String s){
		if(emptyTree()){
			Node root = new Node();
			root.setLeaf(true);
			root.setRoot(true);
			this.root=root;
			this.headLeaf=root;
			root.getNodeData().insertSorted(s);
		}else if(onlyOneRoot()){
			//如果只有一个根节点，也就是说这个根节点也是叶子节点
			root.getNodeData().insertSorted(s);
			//首先插入这个数据，然后判断是否需要分裂
			int size = root.getNodeData().size();
			if(size == degree){
				//取出中间值，分裂
				String middle = root.getNodeData().getMiddle();
				//分裂为两个
				Node node1 = root;
				Node node2 = new Node();
				node2.setLeaf(true);
				node1.setRoot(false);
				node1.setNextLeaf(node2);
				SortedArrayList<String> data1 = node1.getNodeData().getBelowMiddle(middle);
				SortedArrayList<String> data2 = node2.getNodeData().getOverMiddle(middle);
				node1.setNodeData(data1);
				node2.setNodeData(data2);
				
				Node newRoot = new Node();
				newRoot.setRoot(true);
				this.root = newRoot;
				root.getChildren().add(node1);
				root.getChildren().add(node2);
				root.getNodeData().insertSorted(middle);
			}else{
				root.getNodeData().insertSorted(s);
			}
		}else{
			System.out.println("value to big!");
		}
	}
	
	public boolean emptyTree(){
		return root==null;
	}
	
	public boolean onlyOneRoot(){
		return this.root==this.headLeaf;
	}

	@Override
	public String toString() {
		if(emptyTree()){
			return "emptyTree";
		}else if(onlyOneRoot()){
			return "all data in root:"+root.getNodeData().toString();
		}else{
			//有多个节点，分别打印出索引节点和数据节点
			
		}
	}
	
	
	
}
