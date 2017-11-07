package test;

import java.util.UUID;

import entity.BPlusTree;
import entity.Node;

/**
 * Created by Administrator on 2017/10/31.
 */
public class Test {
    public static void main(String[] args) {
        BPlusTree bptree = new BPlusTree(20);
        for(int i=0;i<300;i++){
        	String key = UUID.randomUUID().toString().substring(3, 10).replaceAll("-", "");
        	System.out.println("insert key:"+key);
            bptree.add(key);
        }
        System.out.println("*******************************************************************");
        System.out.println(bptree);
        System.out.println("*******************************************************************");
        Node n = bptree.getHeadLeaf();
        while(true){
        	if(n==null)
        		break;
        	System.out.println(n.getNodeData());
        	n = n.getNextLeaf();
        }
    }
}
