package tools;

import java.util.ArrayList;
import java.util.Collections;

public class SortedArrayList<T> extends ArrayList<T> implements SortedList<T>{

	public void insertSorted(T value) {
		add(value);
		Comparable<T> cmp = (Comparable<T>) value;
		for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
			Collections.swap(this, i, i-1);
	}
	
	public T getMiddle(){
		return this.get(this.size()/2);
	}
	
	public <T extends Comparable<T>> SortedArrayList<T> getOverMiddle(T middle){
		SortedArrayList<T> result = new SortedArrayList<T>();
		for(int i =0;i<size();i++){
			T t = (T) get(i);
			if(t.compareTo(middle) >= 0){
				result.add(t);
			}
		}
		return result;
	}
	
	public <T extends Comparable<T>> SortedArrayList<T> getBelowMiddle(T middle){
		SortedArrayList<T> result = new SortedArrayList<T>();
		for(int i =0;i<size();i++){
			T t = (T) get(i);
			if(t.compareTo(middle) < 0){
				result.add(t);
			}
		}
		return result;
	}
	
	
	//
}
