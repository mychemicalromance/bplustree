package test;

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