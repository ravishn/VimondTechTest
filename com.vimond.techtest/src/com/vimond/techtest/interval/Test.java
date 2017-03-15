package com.vimond.techtest.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vimond.techtest.interval.AnnotatedPoint.PointTypeEnum;

/**
 * Class to test the Interval program with several inputs
 * @author Ravish
 *
 */
public class Test {

	/**
	 * main method to provide inputs to the include and exclude intervals and call the sweep algorithm
	 * to remove the exclude intervals
	 * @param args
	 */
	public static void main(String[] args) {

		// Provide interval inputs for include and exclude here
		List<Interval> include = Arrays.asList(new Interval(10, 100), new Interval(200, 300), new Interval(400, 500));
		List<Interval> exclude = Arrays.asList(new Interval(95, 205), new Interval(410, 420));

		// initialise list with include and exclude intervals
		List<AnnotatedPoint> queue = initQueue(include, exclude);

		// run sweep and store the result in the list
		List<Interval> result = doSweep(queue);

		// print the result
		for (Interval i : result) {
			System.out.println(i);
		} // end for
	} // end main()

	/**
	 * initialise queue with include and exclude intervals provided in the input
	 * @param include
	 * @param exclude
	 * @return
	 */
	private static List<AnnotatedPoint> initQueue(List<Interval> include, List<Interval> exclude) {

		// annotate all points and add them to a list
		List<AnnotatedPoint> queue = new ArrayList<>();
		// add include intervals to the list
		for (Interval i : include) {
			queue.add(new AnnotatedPoint(i.start, PointTypeEnum.Start));
			queue.add(new AnnotatedPoint(i.end, PointTypeEnum.End));
		} // end for
		// add exclude intervals to the list
		for (Interval i : exclude) {
			queue.add(new AnnotatedPoint(i.start, PointTypeEnum.GapStart));
			queue.add(new AnnotatedPoint(i.end, PointTypeEnum.GapEnd));
		} // end for

		if(exclude.isEmpty()) {

			return queue;
		} // end if

		// sort the queue
		Collections.sort(queue);

		return queue;
	} // end initQueue()

	/**
	 * sweep through the queue to find out gaps between intervals based on inputs
	 * passed in the include and exclude intervals
	 * @param queue
	 * @return
	 */
	private static List<Interval> doSweep(List<AnnotatedPoint> queue) {
		List<Interval> result = new ArrayList<>();

		// isInterval: #Start seen > #End seen
		boolean isInterval = false;

		// isGap: #GapStart seen > #GapEnd seen
		boolean isGap = false;
		int intervalStart = 0;

		// iterate through the queue to find out gaps in the intervals
		for (AnnotatedPoint point : queue) {
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
			} // end switch
		} // end for

		return result;
	} // end doSweep()
} // end Test class
