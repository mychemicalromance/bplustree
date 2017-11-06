package test;

import java.util.ArrayList;
import java.util.UUID;

import entity.BPlusTree;

/**
 * Created by Administrator on 2017/10/31.
 */
public class Test {
    public static void main(String[] args) {
        BPlusTree bptree = new BPlusTree(5);
        ArrayList<String> list = new ArrayList<>();
        
        for(int i=0;i<22;i++){
        	String key = UUID.randomUUID().toString().substring(3, 10).replaceAll("-", "");
        	if(list.contains(key)){
        		System.out.println("again=============================");
        		continue;
        	}
        	list.add(key);
            bptree.add(key);
        }
        System.out.println("===============================================");
        System.out.println("source data:"+list.toString());
        System.out.println("************************************************");
        System.out.println(bptree);
    }
}
