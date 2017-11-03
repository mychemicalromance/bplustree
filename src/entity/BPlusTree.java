package entity;

import tools.SortedArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
				SortedArrayList<String> data2 = node1.getNodeData().getOverMiddle(middle);
				node1.setNodeData(data1);
				node2.setNodeData(data2);
				
				Node newRoot = new Node();
				newRoot.setRoot(true);
				this.root = newRoot;
				root.getChildren().add(node1);
				root.getChildren().add(node2);
				node1.setParent(root);
				node2.setParent(root);
				root.getNodeData().insertSorted(middle);
			}
		}else{
			//这种情况下树至少有三个节点，此种情况下要考虑叶子分裂的问题，并且有可能导致根分裂，
			//最复杂的情况是整个树增加一层，需要递归调用
			//首先找到需要将值插入的叶子节点，插入之后判断是否需要分裂，如果需要分裂，分裂完成后再将中间值插入父节点，以此类推
			Node currentNode = root;
			while(true){
				if(currentNode.isLeaf()){
					break;
				}
				List<String> cnDatas = currentNode.getNodeData();
				int choseChild = 0;
				for(int i=0;i<=cnDatas.size();i++){
					if(i==0){
						String upper = cnDatas.get(0);
						if(s.compareTo(upper)<0){
							choseChild = i;
							break;
						}
					}else if(i==cnDatas.size()){
						String lower = cnDatas.get(i-1);
						if(s.compareTo(lower)>=0){
							choseChild = i;
							break;
						}
					}else{
						String lower = cnDatas.get(i-1);
						String upper = cnDatas.get(i);
						if(s.compareTo(lower)>=0 && s.compareTo(upper)<0){
							//刚好位于整个区间
							choseChild = i;
							break;
						}
					}
				}
				currentNode = currentNode.getChildren().get(choseChild);
			}
			System.out.println("chose leaf:"+currentNode.getNodeData());
			//开始进行insert和判断
			while(true){
				Map<String,Object> result = doInsert(currentNode, s);
				if(result == null){
					break;
				}else{
					currentNode = (Node) result.get("currentNode");
					s = (String) result.get("s");
				}
			}
			
			
		}
	}
	
	private Map<String,Object> doInsert(Node node,String s){
		//首先进行数据插入
		node.getNodeData().insertSorted(s);
		if(node.getNodeData().size() == degree){
			//该节点已满，开始分裂，并且将中间值传递给父节点，然后再次执行父节点的数据插入
			String middle = node.getNodeData().getMiddle();
			if(node.isLeaf()){
				Node parent = node.getParent();
				Node newNode = new Node();
				newNode.setLeaf(node.isLeaf());
				newNode.setParent(parent);
				SortedArrayList<String> dataLeft = node.getNodeData().getBelowMiddle(middle);
				SortedArrayList<String> dataRight = node.getNodeData().getOverMiddle(middle);
				newNode.setNodeData(dataRight);
				node.setNodeData(dataLeft);
				
			}
			
			
		}
		return null;
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
			//有多个节点，分别打印出索引节点和数据节点，广度优先遍历
			StringBuilder sb = new StringBuilder("index data:");
			List<Node> ff = new ArrayList<>();
			ff.add(this.root);
			while(true){
				List<Node> list = this.printNode(ff,sb);
				if(list==null || list.size()==0){
					break;
				}else{
					ff=list;
				}
			}
			return sb.toString();
		}
	}

	private List<Node> printNode(List<Node> nodes,StringBuilder sb){
		sb.append(System.lineSeparator());
		if(nodes.get(0).isLeaf()){
			sb.append("real data:");
			sb.append(System.lineSeparator());
		}
		List<Node> result = new ArrayList<>();
		for(Node node:nodes){
			sb.append(node.getNodeData().toString()).append(";");
			if(node.getChildren()!=null && node.getChildren().size()>0){
				result.addAll(node.getChildren());
			}
		}
		return result;
	}
	
	
	
}
