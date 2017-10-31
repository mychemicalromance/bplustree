package test;

import entity.BPlusTree;

import java.util.UUID;

/**
 * Created by Administrator on 2017/10/31.
 */
public class Test {
    public static void main(String[] args) {
        BPlusTree bptree = new BPlusTree(7);
        for(int i=0;i<7;i++){
            bptree.add(UUID.randomUUID().toString());
        }
        System.out.println(bptree);
    }
}
