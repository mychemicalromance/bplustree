package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.UUID;

public class TestArrayListSorted {
	public static void main(String[] args) {
		SortedLinkedList sll = new SortedLinkedList();
		for(int i=0;i<10;i++){
			sll.add(UUID.randomUUID().toString());
		}
		System.out.println(sll);
	}
}

//use linkedlist
class SortedLinkedList{
	private LinkedList<String> inner;
	public SortedLinkedList(){
		inner = new LinkedList<String>();
	}
	public void add(String str){
		int i=0;
		for(String s:inner){
			if(s.compareTo(str)>0){
				break;
			}
			i++;
		}
		inner.add(i,str);	
	}
	@Override
	public String toString() {
		return "SortedLinkedList [inner=" + inner + "]";
	}
}


class SortedArrayList<T> extends ArrayList<T> {
    @SuppressWarnings("unchecked")
    public void insertSorted(T value) {
        add(value);
        Comparable<T> cmp = (Comparable<T>) value;
        for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
            Collections.swap(this, i, i-1);
    }
}