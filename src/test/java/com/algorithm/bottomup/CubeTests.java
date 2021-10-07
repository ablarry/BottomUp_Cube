package com.algorithm.bottomup;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.algorithm.utils.Util;
import org.junit.Test;

public class CubeTests {

	@Test
	public void sortTest() {
		long[][] values = { { 0, 1 }, { 3, 0 }, { 2, 2 } };
		long[][] sortedValues = { { 0, 1 }, { 2, 2 }, { 3, 0 } };
		long[][] sortedValues2 = { { 3, 0 }, { 0, 1 }, { 2, 2 } };
		Arrays.sort(values, new ColumnComparator<long[]>(0));
		assertTrue(Arrays.deepEquals(values, sortedValues));
		Arrays.sort(values, new ColumnComparator<long[]>(1));
		assertTrue(Arrays.deepEquals(values, sortedValues2));

	}
	@Test
	public void sortTest2() {
		long[][] values = { { 0, 1 }, { 3, 0 }, { 2, 2 } };
		long[][] sortedValues = { { 0, 1 }, { 2, 2 }, { 3, 0 } };
		long[][] sortedValues2 = { { 3, 0 }, { 0, 1 }, { 2, 2 } };
		Arrays.sort(values, new ColumnComparator<long[]>(0));
		assertTrue(Arrays.deepEquals(values, sortedValues));
		Arrays.sort(values, new ColumnComparator<long[]>(1));
		assertTrue(Arrays.deepEquals(values, sortedValues2));

	}
	@Test
	public void partitionTest() {
		Integer[][] values = { { 0, 1 }, { 0, 2 }, { 3, 0 }, { 3, 4 }, { 2, 2 } };
		Integer[][][] partitionedValues = { { { 0, 1 }, { 0, 2 } },
				{ { 3, 0 }, { 3, 4 } }, { { 2, 2 } } };
		Integer[][][] x = { { { 0 } } };
		ArrayList<Integer[][]> partitions = Util.<Integer>partitionByColumn(values, 0);
		Integer[][][] partitionArray = partitions.toArray(x);
		assertTrue(Arrays.deepEquals(partitionArray, partitionedValues));
	}
	@Test
	public void readFile() throws IOException {
		String[][] dataset = Util.readFile("records.csv");
		String[][] values = { { "1", "2" },
				{ "1", "3" },
				{ "14","2"},
				{ "3", "4" }
		};
		assertTrue(Arrays.deepEquals(dataset, values));
	}


	@Test
	public void materializeTest() {
		int threshold = 1;

		String[][] values = Util.readFile("data.csv");
		CubingAlgorithm algo = new BottomUpCubingAlgorithm<String>(values,	new Measure.CountMeasure(), threshold);
		ResultCollector.HashMapResultCollector hmCollector = new ResultCollector.HashMapResultCollector();
		algo.materializeCube(hmCollector);
		for (Map.Entry<String, Long> r:hmCollector.results.entrySet()){
			System.out.println(r.getKey() + r.getValue());

		}
	}
	@Test
	public void materializeTest1() {
		int threshold = 0;
		String[][] values = { { "1", "2" },
				{ "1", "3" },
				{ "14","2"},
				{ "3", "4" }
		};
		CubingAlgorithm algo = new BottomUpCubingAlgorithm<String>(values,	new Measure.CountMeasure(), threshold);
		ResultCollector.HashMapResultCollector hmCollector = new ResultCollector.HashMapResultCollector();
		algo.materializeCube(hmCollector);
		for (Map.Entry<String, Long> r:hmCollector.results.entrySet()){
			System.out.println(r.getKey() + r.getValue());

		}
		assertTrue(hmCollector.results.get("{}") == 4);
		assertTrue(hmCollector.results.get("{1=2}") == 2);
	}

	public void extractDimensions(){

	}

}
