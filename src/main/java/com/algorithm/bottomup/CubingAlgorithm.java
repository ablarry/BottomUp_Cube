package com.algorithm.bottomup;


public abstract class CubingAlgorithm<T> {

	T[][]  data;
	Measure measure;
	int threshold;
	
	public CubingAlgorithm(T[][]  data, Measure measure, int threshold) {
		this.data = data;
		this.measure = measure;
		this.threshold = threshold;
	}
	
	public abstract void materializeCube(ResultCollector result);
	
}
