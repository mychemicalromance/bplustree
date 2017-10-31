package tools;

import java.util.Iterator;
import java.util.LinkedList;

public class SortedLinkedList<T> extends LinkedList<T> implements SortedList<T>{

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
