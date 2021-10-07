package com.algorithm.bottomup;

/**
 * Measures to apply against the tabular dataset (represented as long[][])
 * TODO: Add attributes to denote monotonic, algebraic
 */
public interface Measure<T> {

	long aggregate(T[][] values);

	/**
	 * Computes SUM() of the assigned column.

	public static class SumMeasure implements Measure<T> {

		final int column;

		public SumMeasure(int column) {
			this.column = column;
		}

		@Override
		// FIXME: does not check for overflow
		public long aggregate(T[][] values) {
			long sum = 0;
			for (T[] tuple : values) {
				sum += tuple[this.column];
			}
			return sum;
		}

	}	 */

	/**
	 * Computes COUNT() of the dataset
	 */
	public static class CountMeasure<T> implements Measure<T> {
		@Override
		public long aggregate(T[][] values) {
			return values.length;
		}
	}
}