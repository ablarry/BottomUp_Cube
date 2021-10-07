package com.algorithm.bottomup;

import com.algorithm.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Implementation of 
 * Bottom-Up Cubing Algorithm for Iceberg Cubes, Beyer et al, SIGMOD 99.
 * 
 * Assumes Measure is monotonically increasing i.e. M(A union B) >= M(A)
 * 
 * Successively sort and partition data by columns, aborting sort if partition's
 * aggregate doesn't meet threshold, since it's sub-partitions will apriori not
 * meet threshold either.
 */
 
public class BottomUpCubingAlgorithm<T> extends CubingAlgorithm {

	public BottomUpCubingAlgorithm(T[][]  data, Measure measure, int threshold) {
		super(data, measure, threshold);
	}

	/**
	 * Recursively partition dataset, sort, partition and aggregate
	 * 
	 * @param result
	 * @param data
	 * @param columnsSoFar
	 * @param sortByColumns
	 */
	public void buc(ResultCollector result, T[][] data, HashMap<Integer, T> columnsSoFar, int[] sortByColumns) {
		long aggregate = this.measure.aggregate(data);
		if (aggregate < this.threshold) {
			return; // abort further computation
		}
		result.collect(columnsSoFar, aggregate);
		// sort and partition
		for (int column : sortByColumns) {
			Arrays.sort(data, new ColumnComparator<T[]>(column));
			ArrayList<T[][]> partitions = Util.<T>partitionByColumn(data,column);
			for (T[][] p : partitions) {
				HashMap<Integer, T> newColumnsSoFar = new HashMap<Integer, T>(columnsSoFar);
				newColumnsSoFar.put(column, p[0][column]);
				int[] newSortByColumns = Util.subtract(sortByColumns, column);
				Arrays.sort(newSortByColumns);
				buc(result, p, newColumnsSoFar, newSortByColumns);
			}
		}
	}

	@Override
	public void materializeCube(ResultCollector result) {
		HashMap<Integer, T> sofar = new HashMap<Integer, T>();
		this.buc(result, (T[][])this.data, sofar, Util.range(this.data[0].length));
	}

}
