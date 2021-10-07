package com.algorithm.bottomup;

import java.util.Comparator;

/**
 * Sorts tabular data according to configured column
 * 
 * @param <T> : Ignored, this is only for long[][] data
 */
public class ColumnComparator<T> implements Comparator<T> {

	final int column;
	
	public ColumnComparator(int column) {
		this.column = column;
	}

	@Override
	public int compare(T a1, T a2) {
		String[] A1 = (String[]) a1;
		String[] A2 = (String[]) a2;
		return A1[column].compareTo(A2[column]);
	}
	
}