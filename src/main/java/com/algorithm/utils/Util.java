package com.algorithm.utils;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import gnu.trove.set.hash.TIntHashSet;

/**
 * Miscellaneous Utility functions and classes.
 */
public class Util {

	// Clone of python's range() function, returns [0..n-1]
	public static int[] range(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}
		return result;
	}

	/**
	 * @param data
	 * @param column
	 * @return data GROUP BY column (NOTE: No sorting happens here, so if you
	 *         want SQL semantics, please SORT before!)
	 */
	public static <T> ArrayList<T[][]> partitionByColumn(T[][] data,
			int column) {

		ArrayList<T[][]> parts = new ArrayList<T[][]>();

		// edge case -- if only one tuple, send back as group
		if (data.length == 1) {
			parts.add(data);
			return parts;
		}

		int marker = 0;
		T value = data[0][column];

		for (int i = 1; i < data.length; i++) {
			if (!data[i][column].equals(value)) { // new group! let's copy everything
				// we've seen so far and reset
				parts.add(Arrays.copyOfRange(data, marker, i));
				marker = i;
				value = data[i][column];
			}
		}

		// copy the last partition
		parts.add(Arrays.copyOfRange(data, marker, data.length));

		return parts;
	}

	/**
	 * @param array
	 * @param val
	 * @return array - val
	 */
	public static int[] subtract(int[] array, int val) {
		TIntHashSet res = new TIntHashSet();
		res.addAll(array);
		res.remove(val);
		return res.toArray();
	}

	public static String toString(int[] arr) {
		StringBuilder sb = new StringBuilder("[");
		for (int i : arr) {
			sb.append(i + " ");
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(long[] arr) {
		StringBuilder sb = new StringBuilder("[");
		for (long i : arr) {
			sb.append(i + " ");
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(HashMap<Integer, Long> map) {
		StringBuilder sb = new StringBuilder("[");
		for (Entry<Integer, Long> entry : map.entrySet()) {
			sb.append(entry.getKey() + ":" + entry.getValue() + " ");
		}
		sb.append("]");
		return sb.toString();
	}

	public static String[][] readFile(String fileName){
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				records.add(Arrays.asList(values));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[][] dataset = records
				.stream()
				.map(
						(l) -> l.toArray(new String[l.size()])
				)
				.collect(Collectors.toList())
				.toArray(new String[records.size()][]);
		return dataset;
	}

}
