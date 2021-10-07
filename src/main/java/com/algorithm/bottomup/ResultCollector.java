package com.algorithm.bottomup;

import java.util.HashMap;


/**
 * Ways to collect the materialized cube.
 * 
 */
public interface ResultCollector<T> {

	public void collect(HashMap<Integer, T> key, long value);
	

	/**
	 * Write to a HashMap. NOTE: May get large!
	 */
	public class HashMapResultCollector implements ResultCollector<Long> {

		//TODO
		public HashMap<String, Long> resultsGeneral = new HashMap<String, Long>();
		// Extract dimensions to use it as unique id

		public HashMap<String, Long> results = new HashMap<String, Long>();

		public void collect(HashMap<Integer, Long> key, long value) {
			/*{} 8974
			{0=25, 1=28} 1  -> {01} 1
			{0=34, 1=18} 1  -> {01} 2
			{0=33, 1=18} 4	-> {01} 6
			{0=70} 1		-> {0} 1*/
			results.put(key.toString(), value);
		}
	}

	/**
	 * Write to Standard Out
	 */
	public class StdOutResultCollector implements ResultCollector<Long> {
		
		public void collect(HashMap<Integer, Long> key, long value) {
			System.out.println(key + " => " + value);
		}
	}
	
	/**
	 * Drop results, but keep count
	 */
	public class NullResultCollector implements ResultCollector<Long> {
		int count = 0;

		public void collect(HashMap<Integer, Long> key, long value) {
			if (++count % 100 == 0) {
				System.err.println(count + " records produced.");
			}
			// do nothing!
		}
	}
}
