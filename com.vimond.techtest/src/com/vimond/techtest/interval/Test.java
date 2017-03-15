package com.vimond.techtest.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vimond.techtest.interval.Point.PointTypeEnum;

/**
 * Class to test the Interval program with several inputs
 * @author Ravish
 *
 */
public class Test {

	public static void main(String[] args) {

		// Example 1
//				List<Interval> include = Arrays.asList(new Interval(10, 100));
//				List<Interval> exclude = Arrays.asList(new Interval(20, 30));

		// Example 2
		//FIXME: merge intervals
		List<Interval> include = Arrays.asList(new Interval(50, 5000), new Interval(10, 100));
		List<Interval> exclude = Arrays.asList();

		//	Example 3
//				List<Interval> include = Arrays.asList(new Interval(10, 100), new Interval(200, 300));
//				List<Interval> exclude = Arrays.asList(new Interval(95, 205));

		//		//Example 4
//				List<Interval> include = Arrays.asList(new Interval(10, 100), new Interval(200, 300), new Interval(400, 500));
//				List<Interval> exclude = Arrays.asList(new Interval(95, 205), new Interval(410, 420));

		List<Point> queue = initQueue(include, exclude);
		List<Interval> result = doSweep(queue);

		// print result
		for (Interval i : result) {
			System.out.println(i);
		}
	}

	private static List<Point> initQueue(List<Interval> include, List<Interval> exclude) {
		// annotate all points and put them in a list
		List<Point> queue = new ArrayList<>();
		for (Interval i : include) {
			queue.add(new Point(i.start, PointTypeEnum.Start));
			queue.add(new Point(i.end, PointTypeEnum.End));
		}
		for (Interval i : exclude) {
			queue.add(new Point(i.start, PointTypeEnum.GapStart));
			queue.add(new Point(i.end, PointTypeEnum.GapEnd));
		}

		if(exclude.isEmpty()) {
			for (Interval i : include) {
				queue.add(new Point(i.start, PointTypeEnum.Start));
				queue.add(new Point(i.end, PointTypeEnum.End));
			}
			return queue;
		}

		// sort the queue
		Collections.sort(queue);

		return queue;
	}

	private static List<Interval> doSweep(List<Point> queue) {
		List<Interval> result = new ArrayList<>();

		// iterate over the queue
		boolean isInterval = false; // isInterval: #Start seen > #End seen
		boolean isGap = false;      // isGap:      #GapStart seen > #GapEnd seen
		int intervalStart = 0;
		for (Point point : queue) {
			switch (point.type) {
			case Start:
				if (!isGap) {
					intervalStart = point.value;
				}
				isInterval = true;
				break;
			case End:
				if (!isGap) {
					result.add(new Interval(intervalStart, point.value));
				}
				isInterval = false;
				break;
			case GapStart:
				if (isInterval) {
					result.add(new Interval(intervalStart, point.value - 1));
				}
				isGap = true;
				break;
			case GapEnd:
				if (isInterval) {
					intervalStart = point.value + 1;
				}
				isGap = false;
				break;
			}
		}

		return result;
	}
}
