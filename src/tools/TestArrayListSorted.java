package tools;

import java.util.*;

public class TestArrayListSorted {
	public static void main(String[] args) {
		SortedArrayList<String> sal = new SortedArrayList<>();
		for(int i=0;i<100;i++){
			sal.insertSorted(UUID.randomUUID().toString());
		}
		System.out.println(sal);
	}
}

class SortedLinkedList<T> extends LinkedList<T> implements SortedList<T>{

	public void insertSorted(T t){
		Iterator<T> it = iterator();
		int i=0;
		while(it.hasNext()){
			Comparable<T> cmp = (Comparable<T>)it.next();
			if(cmp.compareTo(t)>0){
				break;
			}
			i++;
		}
		add(i,t);
	}
}

class SortedArrayList<T> extends ArrayList<T> implements SortedList<T>{

	public void insertSorted(T value) {
		add(value);
		Comparable<T> cmp = (Comparable<T>) value;
		for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
			Collections.swap(this, i, i-1);
	}
}
